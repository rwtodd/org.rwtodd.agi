(ns org-rwtodd.agi-extract.core
  (:require [org-rwtodd.argparse :as ap]
            [org-rwtodd.agi.resources :as res]
            [clojure.java.io :as io]))

(def cmd-args
  "the spec for the command-line arguments to the program"
  {:help    (ap/flag-param "Get Help" :short \h)
   :out     {:arg "DIR" :default "." :doc "Set the output directory to DIR."}
   :info    (ap/flag-param "Get Basic Game Info" :short \i)
   :words   (ap/flag-param "Output Game Words as words.csv" :short \w)
   :objects (ap/flag-param "Output Game Objects as objects.csv" :short \o)})

(defn- stderr
  "print a message to stderr followed by newline"
  [& msgs]
  (binding [*out* *err*]
    (apply println msgs)))

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
           
(defn display-words
  "Output words in CSV format with a header"
  [out words]
  (.write out "Word,Group\n")
  (dorun (map #(.write out (apply format "\"%s\",%d\n" %)) words)))

(defn display-objects
  "Output objects in CSV format with a header"
  [out objs]
  (.write out "Object,\"Starting Room\"\n")
  (dorun (map (fn [o]
                (.write out (format "\"%s\",%d\n" (:name o) (:starting-room o))))
              (:objects objs))))

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
          (display-objects w objects))))))
      
