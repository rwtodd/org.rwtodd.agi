(ns org-rwtodd.agi-extract.core
  (:require [org-rwtodd.argparse :as ap]
            [org-rwtodd.agi.resources :as res]
            [clojure.java.io :as io])
  (:import [javax.sound.midi InvalidMidiDataException
            Sequence  Track MetaMessage  MidiEvent  ShortMessage
            MidiSystem]
           [java.nio.charset StandardCharsets]))


;; ====== Cmdline parsing
(defn which-parser
  "Some resources are numbered, and the user can select which ones to extract."
  [arg]
  (case arg
    ("all" "ALL") []
    (map #(Integer/parseInt %) (.split arg ","))))

(defn reslist-flag
  "A flag which takes a resource list"
  [doc & keys]
  (merge { :doc doc :arg "RESOURCE-LIST" :parser which-parser }
         (apply hash-map keys)))

(def cmd-args
  "the spec for the command-line arguments to the program"
  {:help    (ap/flag-param "Get Help" :short \h)
   :out     {:arg "DIR" :default "." :doc "Set the output directory to DIR."}
   :info    (ap/flag-param "Get Basic Game Info" :short \i)
   :words   (ap/flag-param "Output Game Words as words.csv" :short \w)
   :logic   (reslist-flag  "Extract logic resources to text files" :short \l)
   :csound  (reslist-flag  "Extract sound resources to csound scores" :short \c)
   :midi    (reslist-flag  "Extract sound resources to MIDI files" :short \m)
   :objects (ap/flag-param "Output Game Objects as objects.csv" :short \o)})

;; ====== Utility Functions
(defn- stderr
  "print a message to stderr followed by newline"
  [& msgs]
  (binding [*out* *err*]
    (apply println msgs)))

;; ====== Information Command
(defn display-info
  "Put basic info about the game on screen."
  [info]
  (println (format "Game info:%n")
           (format "- Path: <%s>%n" (:path info))
           (format "- Version: %s %f%n" (or (:prefix info) "") (:version info))
           (format "- %d Sounds, %d Views, %d Pics, %d Logic Scripts%n"
                   (count (:sounds info))
                   (count (:views info))
                   (count (:pics info))
                   (count (:logics info)))))

;; ====== Words Command
(defn display-words
  "Output words in CSV format with a header"
  [out words]
  (.write out "Word,Group\n")
  (dorun (map #(.write out (apply format "\"%s\",%d%n" %)) words)))

;; ====== Objects Command
(defn display-objects
  "Output objects in CSV format with a header"
  [out objs]
  (.write out (format "Number,Object,\"Starting Room\"%n"))
  (dorun (map-indexed
          (fn [i o]
            (.write out (format "%d,\"%s\",%d%n" i (:name o) (:starting-room o))))
          (:objects objs))))

;; ====== Logic Disassembly

(defn display-logic
  "Format a logic script numbered NUM to OUT"
  [out num script]
  (.write out (format ";; Logic Script %04d%n" num))
  ;; for now, just output the bytes...
  (dorun (eduction (partition-all 16)
                   (map (fn [bs]
                          (.write out (pr-str bs))
                          (.write out (format "%n"))))
                   (:byte-codes script)))
          
  ;; now give the table of strings...
  (.write out "\n;; String Table:\n")
  (dorun (map-indexed
          (fn [i txt] (.write out (format "%02d: %s%n" (inc i) txt)))
          (:msgs script))))

(defn process-logics
  "Arrange to write the selected logic resources to text files."
  [game-info out-dir nums]
  (stderr "Writing logic scripts to output dir.")
  (dorun
   (map (fn [num]
          (let [fname (format "logic-%04d.txt" num)
                script (res/load-logic game-info num)]
            (if script
              (do
                (stderr "Writing" fname)
                (with-open [w (io/writer (io/file out-dir fname))]
                  (display-logic w num script)))
              (stderr "Logic script" num "does not exist."))))
        nums)))

;; ====== CSound Command
(def csound-preamble
  "The canned text setting up the csound scores"
  "
t 0 3600 ;; AGI runs in 1/60th second ticks
  
; set up the instruments, in case of MIDI orchestra
i 1  0  0  1   0 4   ;; 4 Rhodes piano 
i 1  0  0  2   0 4   ;; 4 Rhodes piano 
i 1  0  0  3   0 4   ;; 4 Rhodes piano 
  
; set up the panning
i 2  0  0  1 0.5     ;; middle
i 2  0  0  2 0.7     ;; right
i 2  0  0  3 0.3     ;; left

")                  

(defn display-csound
  "Write a csound score for a given SONG, resource num NUMBER, or output OUT"
  [out number song]
  (.write out (format ";; Sound Resource %d%n" number))
  (.write out csound-preamble)
  ;; write the three voices
  (dorun
   (map-indexed (fn [idx notes]
                  (let [curvoice (+ 11 idx)]
                    (.write out (format ";; Start of voice %d (instrument %d)%n"
                                        (inc idx) curvoice))
                    (.write out (format ";;\tstart\tdur\tlevel\tfreq%n"))
                    (dorun (map (fn [note]
                                  (.write out (format "i%d\t%d\t%d\t%d\t%d%n"
                                                      curvoice
                                                      (:time note)
                                                      (:duration note)
                                                      (:attenuation note)
                                                      (:freq note))))
                                notes))
                    (.write out (format ";; End of instrument %d%n%n" curvoice))))
                (:voices song)))
  ;; write the noise channel
  (.write out (format ";; Start of noise channel (instrument 21 linear noise and 31 white noise)%n"))
  (.write out (format ";;\tstart\tdur\tlevel\tfreq%n"))
  (dorun (map (fn [note]
                (.write out (format "i%d\t%d\t%d\t%d\t%d%n"
                                    (case (res/noise-type (:ntype note))
                                      :linear 31
                                      21)
                                    (:time note)
                                    (:duration note)
                                    (:attenuation note)
                                    (:freq note))))
              (:noise song)))
  (.write out (format ";; End of noise channel%n%n"))
  ;; add the mixer instrument
  (.write out (format ";; mixer\n;;\tstart\tdur\trev\tlvl1\tlvl2%n"))
  (.write out (format "i99\t0\t%d\t0.9\t1.0\t1.0%n" (+ 60 (:audible-length song)))))

(defn process-csounds
  "Arrange to write the selected sound resources to csound scores."
  [game-info out-dir nums]
  (stderr "Writing csound scores to output dir.")
  (dorun
   (map (fn [num]
          (let [fname (format "sound-%04d.sco" num)
                sound (res/load-sound game-info num)]
            (if sound
              (do
                (stderr "Writing" fname)
                (with-open [w (io/writer (io/file out-dir fname))]
                  (display-csound w num sound)))
              (stderr "Sound" num "does not exist."))))
        nums)))

;; ====== Midi Command
(def ^:private log2 "constant for freq-to-midi function" (Math/log 2.0))
(defn freq-to-midi
  "Convert an AGI Frequency level to a MIDI note number"
  [freq]
  (int (min 127
            (Math/round (+ 36 (/ (* 12 (Math/log (/ (/ 111860.78125 freq) 64.0))) log2))))))

(defn render-seq
  "Render a sound into a MIDI Sequence."
  [num sound]
  (let [mseq        (Sequence. Sequence/PPQ 60)
        meta-str    (-> (format "AGI Sound Resource %04d" num)
                        (.getBytes StandardCharsets/US_ASCII))
        tempo       (int (/ 60000000 120))]
    ;; write a Tempo track with the title and tempo
    (doto (.createTrack mseq)
      (.add (MidiEvent. (MetaMessage. 0x03 meta-str (count meta-str)) 0))
      (.add (MidiEvent. (MetaMessage. 0x51
                                      (byte-array [(bit-shift-right tempo 16)
                                                   (bit-shift-right tempo 8)
                                                   tempo])
                                      3)
                        0)))

    ;; for each voice, create a track named after it...
    (dorun
     (map-indexed
      (fn [idx notes]
        (let [track    (.createTrack mseq)
              meta-str (-> (format "AGI Voice %d" (inc idx))
                           (.getBytes StandardCharsets/US_ASCII))]
          (.add track (MidiEvent. (MetaMessage. 0x03
                                                meta-str
                                                (count meta-str))
                                  0))
          ;; for each note in the voice, add NOTE_ON messages to the track.
          ;; N.B. The second NOTE_ON has velocity 0, which is equivalent to
          ;; a NOTE_OFF message.
          (dorun (map (fn [note]
                        (let [nn   (freq-to-midi (:freq note))
                              time (* 2 (:time note))
                              dur  (* 2 (:duration note))
                              att  (- 100 (* 6 (:attenuation note)))]
                          (doto track
                            (.add (MidiEvent. (ShortMessage. ShortMessage/NOTE_ON
                                                             idx
                                                             nn
                                                             att)
                                              time))
                            (.add (MidiEvent. (ShortMessage. ShortMessage/NOTE_ON
                                                             idx
                                                             nn
                                                             0)
                                              (+ time dur))))))
                      notes))))
      (:voices sound)))
    
    ;; return the populated sequence
    mseq))
                          
  
(defn process-midis
  "Arrange to write the selected sound resources to midi files."
  [game-info out-dir nums]
  (stderr "Writing midi scores to output dir.")
  (dorun
   (map (fn [num]
          (let [fname (format "sound-%04d.mid" num)
                sound (res/load-sound game-info num)]
            (if sound
              (do
                (stderr "Writing" fname)
                (MidiSystem/write (render-seq num sound)
                                  1
                                  (io/file out-dir fname)))
              (stderr "Sound" num "does not exist."))))
        nums)))

;; ====== Main Program
(defn -main
  "The main program"
  [& argv]
  (let [args (ap/parse cmd-args argv)]
    (when (or (:help args) (> (count (:free-args args)) 1))
      (stderr "Usage: extract [game-path] [opts]\n\n") (stderr (ap/help-text cmd-args))
      (System/exit 1))
    (let [game-info (res/load-game-info (or (first (:free-args args)) ".") false)
          words     (when (:words args) (res/load-words-tok (:path game-info)))
          objects   (when (:objects args) (res/load-objects (:path game-info) (:version game-info)))]
      ;; ok, now perform the needed actions
      (when (:info args)
        (display-info game-info))
      (when (:words args)
        (stderr "Writing words.csv to output dir.")
        (with-open [w (io/writer (io/file (:out args) "words.csv"))]
          (display-words w words)))
      (when (:objects args)
        (stderr "Writing objects.csv to output dir.")
        (with-open [w (io/writer (io/file (:out args) "objects.csv"))]
          (display-objects w objects)))
      (when-let [logics (:logic args)]
        (process-logics game-info
                        (:out args)
                        (if (zero? (count logics))
                          (range (count (:logics game-info)))
                          logics)))
      (when-let [csounds (:csound args)]
        (process-csounds game-info
                         (:out args) 
                         (if (zero? (count csounds))
                           (range (count (:sounds game-info)))
                           csounds)))
      (when-let [midis (:midi args)]
        (process-midis game-info
                       (:out args) 
                       (if (zero? (count midis))
                         (range (count (:sounds game-info)))
                         midis))))))

;; ====== end of file
;; Local Variables:
;; page-delimiter: "^;; ======"
;; End:
