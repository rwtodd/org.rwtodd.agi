(ns org.rwtodd.agi-to-csound.core
  (:require [org.rwtodd.agi-game.resources :as res]
            [clojure.java.io :as io])
  (:gen-class))

(defn csound-duration [dur] (/ dur 10.0))

;; we need 16 levels here, ending in 0..
(def csound-db (conj (into [] (map double (range 90 60 -2))) 0.0))

(defn csound-freq [agi-freq]
  (if (> agi-freq 0.0)
    (/ 111860.78125 agi-freq)
    440.0))

(defn is-audible?
  "Determines if a track has any audible notes"
  [data]
  (some #(< (.attenuation %) 15) data))

(defn write-voice-chan
  "Writes a channel's worth of voice data."
  [chan pan]
  (when (is-audible? chan)
    (println "; next voice")
    (dorun (map
            (fn [time tone]
              (println (format "i 1\t%s\t%f\t%f\t%f\t%f"
                               time
                               (csound-duration (:duration tone))
                               (csound-db (:attenuation tone))
                               (csound-freq (:freq tone))
                               pan)))
            (cons "0" (repeat "+")) ; times
            chan))))

(defn to-csound
  "Convert a sound resource to a csound score. It is assumed that:
  instrument 1 is for square-waves, instrument 2 is for white noise,
  instrument 3 is for linear noise, and instrument 99 is for reverb.

  Prints the result to *out*, which can obviously be redirected
  with `bind`."
  ([{:keys [duration channels]} {:keys [panning]}]
   (println "t 0 360 ; 1/6th second")
   (dorun (map write-voice-chan
               (take 3 channels)
               (or panning [0.5 0.3 0.7])))
   (println "; reverb --")
   (println (format "i 99\t0\t%f"
                    (csound-duration duration))))
  ([game num opts] (to-csound (res/load-resource game :sound num) opts)))

(defn to-csound-file
  ([sound fname opts]
   (with-open [wtr (io/writer fname)]
     (binding [*out* wtr]
       (to-csound sound opts))))
  ([game num fname opts]
   (to-csound-file (res/load-resource game :sound num) fname opts)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
