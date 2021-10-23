(ns org-rwtodd.agi-extract.core
  (:require [org-rwtodd.argparse :as ap]
            [org-rwtodd.agi.resources :as res]))

(def cmd-args
  "the spec for the command-line arguments to the program"
  {:help  (ap/flag-param "Get Help" :short \h)})

(defn- stderr
  "print a message to stderr followed by newline"
  [& msgs]
  (binding [*out* *err*]
    (apply println msgs)))

(defn -main
  "The main program"
  [& argv]
  (let [args (ap/parse cmd-args argv)]
    (when (:help args)
      (stderr "Usage: extract [opts]\n\n" (ap/help-text cmd-args))
      (System/exit 1))
    (println "hello")))
