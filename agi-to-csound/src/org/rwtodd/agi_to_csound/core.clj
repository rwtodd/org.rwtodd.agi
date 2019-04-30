(ns org.rwtodd.agi-to-csound.core
  (:require [org.rwtodd.agi-game.resources :as res]
            [clojure.java.io :as io])
  (:gen-class))

;; for repeats+fades, we need to be able to copy just a portion
;; of a channel...
(defn running-total [chan]
  (loop [loc  0
         chan chan
         ans (transient [])]
    (if (seq chan)
      (let [newloc (+ loc (:duration (first chan)))]
        (recur  newloc
                (next chan)
                (conj! ans (assoc (first chan) :end-time newloc))))
      (persistent! ans))))

(defn truncate-channel 
  "Truncate a channel of commands to dur seconds."
  [dur chan]
  (if (seq chan)
    (let [agidur (* dur 60)
          truncated (into [] (take-while (fn [note] (< (:end-time note) agidur))
                                         (running-total chan)))
          last-note (peek truncated)
          off-by    (- (:end-time last-note) agidur)]
      (if (pos? off-by)
        (conj (pop truncated) (update last-note :dur (fn [d] (- d off-by))))
        truncated))
    nil))

(defn csound-duration [dur] (/ dur 10.0))

;; we need 16 levels here, ending in -200
(def csound-db (conj (into [] (map double (range -20 -63 -3))) -200.0))

(defn csound-freq [agi-freq]
  (if (> agi-freq 0.0)
    (/ 111860.78125 agi-freq)
    440.0))

(defn is-audible?
  "Determines if a track has any audible notes"
  [data]
  (some #(< (.attenuation %) 15) data))

(defn write-voice-chan
  "Writes a channel's worth of voice data, using pan and damping info"
  [chan pan damping]
  (when (is-audible? chan)
    (println "; next voice")
    (dorun (map
            (fn [time tone]
              (println (format "i 1\t%s\t%f\t%f\t%f\t%f"
                               time
                               (csound-duration (:duration tone))
                               (- (csound-db (:attenuation tone)) damping)
                               (csound-freq (:freq tone))
                               pan)))
            (cons "0" (repeat "+")) ; times
            chan))))

(defn write-noise-chan
  "Write's a noise channel... note needs chan3"
  [chan damping]
  (when (is-audible? chan)
    (println "; noise channel")
    (dorun (map
            (fn [time noise]
              (let [white? (= (:noise noise) :white)
                    base-freq (csound-freq (:freq noise))]
                (println (format "i %d\t%s\t%f\t%f\t%f"
                                 (if white? 2 3)
                                 time
                                 (csound-duration (:duration noise))
                                 (- (+ (csound-db (:attenuation noise)) 15)
                                    damping)
                                 (/ base-freq (if white? 4.0 2.0))))))
            (cons "0" (repeat "+"))
            chan))))
                    
(defn write-channels
  "Write the channels to a score file, panned and damped"
  [channels panning damping]
   (println "t 0 360 ; 1/6th second")
   (dorun (map write-voice-chan
               (take 3 channels)
               panning
               damping))
  (write-noise-chan (nth channels 3 nil) (nth damping 3)))

(defn write-reverb
  "Write the reverb statement into the score.
    dur=duration revt=reverb time
    fade-start=level at start fade-end=level at end"
  [dur revt fade-start fade-end]
  (println (format "i 99\t0\t%f\t%f\t%f\t%f"
                   (csound-duration dur)
                   revt
                   fade-start
                   fade-end)))

(defn to-csound
  "Convert a sound resource to a csound score. It is assumed that:
  instrument 1 is for square-waves, instrument 2 is for white noise,
  instrument 3 is for linear noise, and instrument 99 is for reverb.

  Prints the result to *out*, which can obviously be redirected
  with `bind`."
  ([{:keys [duration channels]} {:keys [panning damping reverb repeat]
                                 :or {panning [0.5 0.7 0.3 0.5]
                                      damping [0 0 0 0]
                                      reverb 0.9
                                      repeat nil}
                                 :as opts}]
   (println ";; options:" opts)
   (write-channels channels panning damping)
   (write-reverb duration reverb 1.0 1.0)
   (when repeat
     (let [[rep-full rep-fade] repeat
           rep-total           (+ rep-full rep-fade)]
     ;; this could be more elegant... a lot of copy+paste here
     (println ";; repeat and fade...")
     (println "s")
     (write-channels (map (partial truncate-channel rep-total) channels)
                     panning
                     damping)
     (write-reverb (* rep-full 60) reverb 1.0 1.0)
     (write-reverb (* rep-fade 60) reverb 1.0 0.001))))
  ([game num opts] (to-csound (res/load-resource game :sound num) opts)))

;; possible options:
;;   :repeat [s1 s2] -- repeat s1+s2 seconds of the piece, fading out s2
;;   :panning [w x y z] -- pan the channels from left (0) to right (1)
;;   :damping [w x y z] -- dampen the channels by some number of dB
;;   :reverb secs     -- reverb decay time in secs 
(defn to-csound-file
  ([sound fname opts]
   (with-open [wtr (io/writer fname)]
     (binding [*out* wtr]
       (to-csound sound opts))))
  ([game num fname opts]
   (to-csound-file (res/load-resource game :sound num)
                   (or fname (format "examples\\%s-s%d.sco" (name game) num))
                   opts)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
