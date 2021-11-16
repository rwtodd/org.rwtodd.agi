(ns org-rwtodd.agi.disassemble
  (:use     [org-rwtodd.agi.res-decode])
  (:require [clojure.java.io :as io]))

;; ====== Utilities
(defn deepen
  "Deepen the provided indention level, INDENT"
  [indent]
  (str indent "    "))

;; ====== Supplemental info

(def var-info
  "Give info about interpreter-set vars"
  ["Current room number" ;; 0
   "Previous room number"
   "Border touched by EGO 0/none 1/top 2/right 3/bottom 4/left"
   "Current Score"
   "Number of object (other than EGO) touching the boarder"
   "The code of border touched by the object in v4" ;; 5
   "Direction of EGO's motion 1/N 3/E 5/S 7/W 0/none"
   "Maximum score"
   "Free pages in memory"
   "if non-zero, number of the word which wasn't found"
   "Time delay in interpreter cycles in 1/20s" ;; 10
   "Clock seconds"
   "Clock minutes"
   "Clock hours"
   "Clock days"
   "Joystick sensitivity" ;; 15
   "ID of the view associated with EGO"
   "Interpreter error code"
   "Additional error info"
   "Key pressed on keyboard"
   "Computer type. IBM=0"   ;; 20
   "If f15=0 and v21 != 0, close window in 0.5*v21 secs"
   "Sound generator type 1/PC 3/Tandy"
   "Sound volume"
   "29h"
   "ID number of item selected using status cmd" ;;25
   "Graphics 0/CGA 2/Hercules 3/EGA"])

(def flg-info
  "Give info about interpreter-set flags"
  ["EGO base line is on pri-3 pixels (water surface)" ;; 0
   "EGO is invisible (completely obscured)"
   "The player has issued a command line"
   "EGO base line has touched a pri-2 pixel (signal)"
   "'said' command has accepted user input"
   "The new room is executed for the first time"  ;; 5
   "'restart_game' command has been executed"
   "when 1, writing to script-buffer is blocked"
   "when 1, v15 determines joystick sensitivity"
   "sound on/off"
   "when 1, built-in debugger is on"  ;; 10
   "set when logic-0 is run for the first time"
   "'restore_game' command has been executed"
   "when 1, allows the 'status' command to select items"
   "when 1, allows the menu to work" ;; 14
   "'print'/'print_at' mode: 0/close-on-<enter> 1/message-stays-up"])

;; ====== Instruction Protocol, Implementations

;; instructions of various types need to know their length and how to pretty-print
(defprotocol Instruction
  "Instructions know their length, and how to pretty-print themselves"
  (byte-size [this] "get the length of the instruction in bytes")
  (pprint [this info base indent] "pretty-print the instruction to *out* from location BASE at 
indention INDENT using INFO for extra data. Returns the next location."))

;; a vector of instructions just prints each in order
(extend-type clojure.lang.PersistentVector ;; TODO...maybe use ISeq?
  Instruction
  (byte-size [this] (transduce (map size) + this))
  (pprint [this info base indent]
    (reduce (fn [loc ins] (+ loc (pprint ins info loc indent)))
            base
            this)))

;; IF-AND is TODO
(deftype IfAndInstruction [^Instruction conditions ^Instruction true-path ^Instruction false-path]
  Instruction
  (byte-size [this] (+ 4
                       (byte-size conditions)
                       (byte-size true-path)
                       (if (nil? false-path)
                         0
                         (+ 3 (byte-size false-path)))))
  (pprint [this info base indent]
    (printf "%04X: %sIF-AND(%n" base indent)
    (let [further (deepen indent)
          base (pprint conditions info (inc base) further)]
      (printf "%04X: %s) {%n" base indent)
      (let [base (pprint true-path info (+ base 3) further)
            base (if (nil? false-path)
                   base
                   (do (printf "%04X: %s} else {%n" base indent)
                       (pprint false-path info (+ base 3) further)))]
        (printf "      %s}%n" indent)
        base))))
          
;; OR is 0xfc ...<tests>... 0xfc
(deftype OrInstruction [^Instruction tests]
  Instruction
  (byte-size [this] (+ 2 (byte-size tests)))
  (pprint [this info base indent]
    (printf "%04X: %sOR(%n" base indent)
    (let [base (pprint tests info (inc base) (deepen indent))]
      (printf "%04X: %s)%n" base indent)
      (inc base))))

;; NOT is 0xfd <<test>>
(deftype NotInstruction [^Instruction wrapped]
  Instruction
  (byte-size [this] (inc (byte-size wrapped)))
  (pprint [this info base indent]
    (pprint wrapped info (inc base) (str indent "NOT "))))

;; The IF 0xff instruction, in cases where the THEN portion does not have room
;; according to the structured code one level above it.
(deftype UnlessGotoInstruction [^Instruction conditions ^Instruction jump]
  Instruction
  (byte-size [this] (+ 4 (byte-size conditions)))
  (pprint [this info base indent]
    (printf "%04X: %sUNLESS(%n" base indent)
    (let [base (pprint conditions info (inc base) (deepen indent))]
      (pprint jump info base (str indent ") ")))))

;; Goto is 0xFE TgtLow TgtHigh 
(deftype GotoInstruction [target]
  Instruction
  (byte-size [this] 3)
  (pprint [this info base indent]
    (printf "%04X: %sGOTO(%04X)%n" base indent (+ base target 3))
    (+ base 3)))

;; There are many BasicInstructions... most byte-codes are basic instructions
(deftype BasicInstruction [name args atype]
  Instruction
  (byte-size [this] (inc args))
  (pprint [this info base indent]
    (printf "%04X: %s%s" base indent name)
    (inc args)))

;; ====== The Basic Instructions
(def test-instructions
  "The if-test components"
  [(->BasicInstruction "UNKNOWN" 0 0x0) ;; 0
   (->BasicInstruction "equaln" 2 0x14) ;; 1
   (->BasicInstruction "equalv" 2 0x44)
   (->BasicInstruction "lessn" 2 0x14)
   (->BasicInstruction "lessv" 2 0x44)
   (->BasicInstruction "greatern" 2 0x14) ;; 5
   (->BasicInstruction "greaterv" 2 0x44) ;; 6
   (->BasicInstruction "isset" 1 0x7)
   (->BasicInstruction "issetv" 1 0x4)
   (->BasicInstruction "has" 1 0x5)
   (->BasicInstruction "obj.in.room" 2 0x45)
   (->BasicInstruction "posn" 5 0x11112) ;; 11
   (->BasicInstruction "controller" 1 0x3)
   (->BasicInstruction "have.key" 0 0x0)
   (->BasicInstruction "said" 0 0x0)  ;; special arg-handling for "said" 14
   (->BasicInstruction "compare.strings" 2 0x88)
   (->BasicInstruction "obj.in.box" 5 0x11112) ;; 16
   (->BasicInstruction "center.posn" 5 0x11112)
   (->BasicInstruction "right.posn" 5 0x11112)])

(def action-instructions
  "The bulk of the byte-codes."
  [(->BasicInstruction "return" 0, 0x0) ;; 0
   (->BasicInstruction "increment" 1 0x4)
   (->BasicInstruction "decrement" 1 0x4)
   (->BasicInstruction "assignn" 2 0x14)
   (->BasicInstruction "assignv" 2 0x44)
   (->BasicInstruction "addn" 2 0x14) ;; 5
   (->BasicInstruction "addv" 2 0x44)
   (->BasicInstruction "subn" 2 0x14)
   (->BasicInstruction "subv" 2 0x44)
   (->BasicInstruction "lindirectv" 2 0x44)
   (->BasicInstruction "rindirect" 2 0x44) ;; 10
   (->BasicInstruction "lindirectn" 2 0x14)
   (->BasicInstruction "set" 1 0x7)
   (->BasicInstruction "reset" 1 0x7)
   (->BasicInstruction "toggle" 1 0x7)
   (->BasicInstruction "set.v" 1 0x4) ;; 15
   (->BasicInstruction "reset.v" 1 0x4)
   (->BasicInstruction "toggle.v" 1 0x4)
   (->BasicInstruction "new.room" 1 0x1)
   (->BasicInstruction "new.room.v" 1 0x4)
   (->BasicInstruction "load.logics" 1 0x1) ;; 20
   (->BasicInstruction "load.logics.v" 1 0x4)
   (->BasicInstruction "call" 1 0x1)
   (->BasicInstruction "call.v" 1 0x4)
   (->BasicInstruction "load.pic" 1 0x4)
   (->BasicInstruction "draw.pic" 1 0x4) ;; 25
   (->BasicInstruction "show.pic" 0 0x0)
   (->BasicInstruction "discard.pic" 1 0x4)
   (->BasicInstruction "overlay.pic" 1 0x4)
   (->BasicInstruction "show.pri.screen" 0 0x0)
   (->BasicInstruction "load.view" 1 0x1) ;; 30
   (->BasicInstruction "load.view.v" 1 0x4)
   (->BasicInstruction "discard.view" 1 0x1)
   (->BasicInstruction "animate.obj" 1 0x2)
   (->BasicInstruction "unanimate.all" 0 0x0)
   (->BasicInstruction "draw" 1 0x2) ;; 35
   (->BasicInstruction "erase" 1 0x2)
   (->BasicInstruction "position" 3 0x112)
   (->BasicInstruction "position.v" 3 0x442)
   (->BasicInstruction "get.posn" 3 0x442)
   (->BasicInstruction "reposition" 3 0x442) ;; 40
   (->BasicInstruction "set.view" 2 0x12)
   (->BasicInstruction "set.view.v" 2 0x42)
   (->BasicInstruction "set.loop" 2 0x12)
   (->BasicInstruction "set.loop.v" 2 0x42)
   (->BasicInstruction "fix.loop" 1 0x2) ;; 45
   (->BasicInstruction "release.loop" 1 0x2)
   (->BasicInstruction "set.cel" 2 0x12)
   (->BasicInstruction "set.cel.v" 2 0x42)
   (->BasicInstruction "last.cel" 2 0x42)
   (->BasicInstruction "current.cel" 2 0x42) ;; 50
   (->BasicInstruction "current.loop" 2 0x42)
   (->BasicInstruction "current.view" 2 0x42)
   (->BasicInstruction "number.of.loops" 2 0x42)
   (->BasicInstruction "set.priority" 2 0x12)
   (->BasicInstruction "set.priority.v" 2 0x42) ;; 55
   (->BasicInstruction "release.priority" 1 0x2)
   (->BasicInstruction "get.priority" 2 0x42)
   (->BasicInstruction "stop.update" 1 0x2)
   (->BasicInstruction "start.update" 1 0x2)
   (->BasicInstruction "force.update" 1 0x2) ;; 60
   (->BasicInstruction "ignore.horizon" 1 0x2)
   (->BasicInstruction "observe.horizon" 1 0x2)
   (->BasicInstruction "set.horizon" 1 0x1)
   (->BasicInstruction "object.on.water" 1 0x2)
   (->BasicInstruction "object.on.land" 1 0x2) ;; 65
   (->BasicInstruction "object.on.anything" 1 0x2)
   (->BasicInstruction "ignore.objs" 1 0x2)
   (->BasicInstruction "observe.objs" 1 0x2)
   (->BasicInstruction "distance" 3 0x422)
   (->BasicInstruction "stop.cycling" 1 0x2) ;; 70
   (->BasicInstruction "start.cycling" 1 0x2)
   (->BasicInstruction "normal.cycle" 1 0x2)
   (->BasicInstruction "end.of.loop" 2 0x72)
   (->BasicInstruction "reverse.cycle" 1 0x2)
   (->BasicInstruction "reverse.loop" 2 0x72) ;; 75
   (->BasicInstruction "cycle.time" 2 0x42)
   (->BasicInstruction "stop.motion" 1 0x2)
   (->BasicInstruction "start.motion" 1 0x2)
   (->BasicInstruction "step.size" 2 0x42)
   (->BasicInstruction "step.time" 2 0x42) ;; 80
   (->BasicInstruction "move.obj" 5 0x71112)
   (->BasicInstruction "move.obj.v" 5 0x71442)
   (->BasicInstruction "follow.ego" 3 0x712)
   (->BasicInstruction "wander" 1 0x2)
   (->BasicInstruction "normal.motion" 1 0x2) ;; 85
   (->BasicInstruction "set.dir" 2 0x42)
   (->BasicInstruction "get.dir" 2 0x42)
   (->BasicInstruction "ignore.blocks" 1 0x2)
   (->BasicInstruction "observe.blocks" 1 0x2)
   (->BasicInstruction "block" 4 0x1111) ;; 90
   (->BasicInstruction "unblock" 0 0x0)
   (->BasicInstruction "get" 1 0x5)
   (->BasicInstruction "get.v" 1 0x4)
   (->BasicInstruction "drop" 1 0x5)
   (->BasicInstruction "put" 2 0x45) ;; 95
   (->BasicInstruction "put.v" 2 0x44)
   (->BasicInstruction "get.room.v" 2 0x44)
   (->BasicInstruction "load.sound" 1 0x1)
   (->BasicInstruction "sound" 2 0x71)
   (->BasicInstruction "stop.sound" 0 0x0) ;; 100
   (->BasicInstruction "print" 1 0x9)
   (->BasicInstruction "print.v" 1 0x4)
   (->BasicInstruction "display" 3 0x911)
   (->BasicInstruction "display.v" 3 0x444)
   (->BasicInstruction "clear.lines" 3 0x111) ;; 105
   (->BasicInstruction "text.screen" 0 0x0)
   (->BasicInstruction "graphics" 0 0x0)
   (->BasicInstruction "set.cursor.char" 1 0x9)
   (->BasicInstruction "set.text.attribute" 2 0x11)
   (->BasicInstruction "shake.screen" 1 0x1) ;; 110
   (->BasicInstruction "configure.screen" 3 0x111)
   (->BasicInstruction "status.line.on" 0 0x0)
   (->BasicInstruction "status.line.off" 0 0x0)
   (->BasicInstruction "set.string" 2 0x98)
   (->BasicInstruction "get.string" 5 0x11198) ;; 115
   (->BasicInstruction "word.to.string" 2 0x8A)
   (->BasicInstruction "parse" 1 0x8)
   (->BasicInstruction "get.num" 2 0x49)
   (->BasicInstruction "prevent.input" 0 0x0)
   (->BasicInstruction "accept.input" 0 0x0) ;; 120
   (->BasicInstruction "set.key" 3 0x311)
   (->BasicInstruction "add.to.pic" 7 0x1111111)
   (->BasicInstruction "add.to.pic.v" 7 0x4444444)
   (->BasicInstruction "status" 0 0x0)
   (->BasicInstruction "save.game" 0 0x0) ;; 125
   (->BasicInstruction "restore.game" 0 0x0)
   (->BasicInstruction "init.disk" 0 0x0)
   (->BasicInstruction "restart.game" 0 0x0)
   (->BasicInstruction "show.obj" 1 0x1)
   (->BasicInstruction "random" 3 0x411) ;; 130
   (->BasicInstruction "program.control" 0 0x0)
   (->BasicInstruction "player.control" 0 0x0)
   (->BasicInstruction "obj.status.v" 1 0x4)
   (->BasicInstruction "quit" 255 0x1)
   (->BasicInstruction "show.mem" 0 0x0) ;; 135
   (->BasicInstruction "pause" 0 0x0)
   (->BasicInstruction "echo.line" 0 0x0)
   (->BasicInstruction "cancel.line" 0 0x0)
   (->BasicInstruction "init.joy" 0 0x0)
   (->BasicInstruction "toggle.monitor" 0 0x0) ;; 140
   (->BasicInstruction "version" 0 0x0)
   (->BasicInstruction "script.size" 1 0x1)
   (->BasicInstruction "set.game.id" 1 0x9)
   (->BasicInstruction "log" 1 0x9)
   (->BasicInstruction "set.scan.start" 0 0x0) ;; 145
   (->BasicInstruction "reset.scan.start" 0 0x0)
   (->BasicInstruction "reposition.to" 3 0x112)
   (->BasicInstruction "reposition.to.v" 3 0x442)
   (->BasicInstruction "trace.on" 0 0x0)
   (->BasicInstruction "trace.info" 3 0x111) ;; 150
   (->BasicInstruction "print.at" 255 0x1119)
   (->BasicInstruction "print.at.v" 255 0x4449)
   (->BasicInstruction "discard.view.v" 1 0x4)
   (->BasicInstruction "clear.text.rect" 5 0x11111)
   (->BasicInstruction "set.upper.left" 2 0x00) ;; 155
   (->BasicInstruction "set.menu" 1 0x9)
   (->BasicInstruction "set.menu.item" 2 0x39)
   (->BasicInstruction "submit.menu" 0 0x0)
   (->BasicInstruction "enable.item" 1 0x3)
   (->BasicInstruction "disable.item" 1 0x3) ;; 160
   (->BasicInstruction "menu.input" 0 0x0)
   (->BasicInstruction "show.obj.v" 1 0x4)
   (->BasicInstruction "open.dialogue" 0 0x0)
   (->BasicInstruction "close.dialogue" 0 0x0)
   (->BasicInstruction "mul.n" 2 0x14) ;; 165
   (->BasicInstruction "mul.v" 2 0x44)
   (->BasicInstruction "div.n" 2 0x14)
   (->BasicInstruction "div.v" 2 0x44)
   (->BasicInstruction "close.window" 0 0x0)
   (->BasicInstruction "unknown170" 1 0x0) ;; 170
   (->BasicInstruction "unknown171" 0 0x0)
   (->BasicInstruction "unknown172" 0 0x0)
   (->BasicInstruction "unknown173" 0 0x0)
   (->BasicInstruction "unknown174" 1 0x0)
   (->BasicInstruction "unknown175" 1 0x0) ;; 175
   (->BasicInstruction "unknown176" 255 0x0) ;; 176
   (->BasicInstruction "unknown177" 1 0x0)
   (->BasicInstruction "unknown178" 0 0x0)
   (->BasicInstruction "unknown179" 4 0x0000)
   (->BasicInstruction "unknown180" 2 0x00) ;; 180
   (->BasicInstruction "unknown181" 0 0x0)])

(defn generate-actions
  "Generate an instruction set for a given version of the AGI engine"
  [version]
  (assoc action-instructions
         134 (->BasicInstruction "quit" (if (< version 2.090) 0 1) 0x1)
         151 (->BasicInstruction "print.at" (if (< version 2.401) 3 4) 0x1119)
         152 (->BasicInstruction "print.at.v"  (if (< version 2.401) 3 4) 0x4449)
         176 (->BasicInstruction "unknown176" (if (== version 3.002086) 1 0) 0x0)))

;; ====== end of file
;; Local Variables:
;; page-delimiter: "^;; ======"
;; End:
