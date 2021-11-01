(ns org-rwtodd.agi-extract.core
  (:require [org-rwtodd.argparse :as ap]
            [org-rwtodd.agi.resources :as res]
            [clojure.java.io :as io]))

;; ====== Cmdline parsing
(defn which-parser
  "Some resources are numbered, and the user can select which ones to extract."
  [arg]
  (case arg
    ("all" "ALL") []
    (map #(Integer/parseInt %) (.split arg ","))))

(def cmd-args
  "the spec for the command-line arguments to the program"
  {:help    (ap/flag-param "Get Help" :short \h)
   :out     {:arg "DIR" :default "." :doc "Set the output directory to DIR."}
   :info    (ap/flag-param "Get Basic Game Info" :short \i)
   :words   (ap/flag-param "Output Game Words as words.csv" :short \w)
   :csound  {:arg "RESOURCE-LIST" :parser which-parser :short \c
             :doc "Extract sound resources to csound scores"}
   :midi    {:arg "RESOURCE-LIST" :parser which-parser :short \m
             :doc "Extract sound resources to MIDI files"}
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
  (println "Game info:\n"
           (format "- Path: <%s>\n" (:path info))
           (format "- Version: %s %f\n" (or (:prefix info) "") (:version info))
           (format "- %d Sounds, %d Views, %d Pics, %d Logic Scripts\n"
                   (count (:sounds info))
                   (count (:views info))
                   (count (:pics info))
                   (count (:logics info)))))

;; ====== Words Command
(defn display-words
  "Output words in CSV format with a header"
  [out words]
  (.write out "Word,Group\n")
  (dorun (map #(.write out (apply format "\"%s\",%d\n" %)) words)))

;; ====== Objects Command
(defn display-objects
  "Output objects in CSV format with a header"
  [out objs]
  (.write out "Object,\"Starting Room\"\n")
  (dorun (map (fn [o]
                (.write out (format "\"%s\",%d\n" (:name o) (:starting-room o))))
              (:objects objs))))

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
  (.write out (format ";; Sound Resource %d\n" number))
  (.write out csound-preamble)
  ;; write the three voices
  (dorun
   (map-indexed (fn [idx notes]
                  (let [curvoice (+ 11 idx)]
                    (.write out (format ";; Start of voice %d (instrument %d)\n"
                                        (inc idx) curvoice))
                    (.write out ";;\tstart\tdur\tlevel\tfreq\n")
                    (dorun (map (fn [note]
                                  (.write out (format "i%d\t%d\t%d\t%d\t%d\n"
                                                      curvoice
                                                      (:time note)
                                                      (:duration note)
                                                      (:attenuation note)
                                                      (:freq note))))
                                notes))
                    (.write out (format ";; End of instrument %d\n\n" curvoice))))
                (:voices song)))
  ;; write the noise channel
  (.write out ";; Start of noise channel (instrument 21 linear noise and 31 white noise)\n")
  (.write out ";;\tstart\tdur\tlevel\tfreq\n")
  (dorun (map (fn [note]
                (.write out (format "i%d\t%d\t%d\t%d\t%d\n"
                                    (case (res/noise-type (:ntype note))
                                      :linear 31
                                      21)
                                    (:time note)
                                    (:duration note)
                                    (:attenuation note)
                                    (:freq note))))
              (:noise song)))
  (.write out ";; End of noise channel\n\n")
  ;; add the mixer instrument
  (.write out ";; mixer\n;;\tstart\tdur\trev\tlvl1\tlvl2\n")
  (.write out (format "i99\t0\t%d\t0.9\t1.0\t1.0\n" (+ 60 (:audible-length song)))))

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
      (when-let [csounds (:csound args)]
        (process-csounds game-info
                         (:out args) 
                         (if (zero? (count csounds))
                           (range (count (:sounds game-info)))
                           csounds))))))
      
;; ====== end of file
;; Local Variables:
;; page-delimiter: "^;; ======"
;; End:
