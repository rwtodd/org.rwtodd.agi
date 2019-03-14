(ns org.rwtodd.agi-to-midi.core
  (:require [org.rwtodd.agi-game.resources :as res])
  (:import (javax.sound.midi Sequence        Track
                             MidiEvent       ShortMessage
                             MidiSystem))
  (:gen-class))

(defn is-audible?
  "Determines if a track has any audible notes"
  [data]
  (some #(< (.attenuation %) 15) data))

(defn scale-velocity
  [atten]
  (Math/round (double (* (- 15 atten) (/ 127 15)))))

(def freq-factor (Math/log10 (Math/pow 2.0 (double (/ 1 12)))))

(defn scale-frequency
  [reset-num]
  (if (zero? reset-num)
    0
    (min 127
         (Math/round (- (/ (Math/log10 (double (/ 111860 reset-num)))
                           freq-factor)
                        36)))))
    
(defn convert-notes
  "Convert the notes of a channel to MIDI short messages."
  [track chan data]
  (loop [time 2
         data data]
    (when data
      (let [cur (first data)
            end-time (+ time (.duration cur))
            note (scale-frequency (.freq cur))
            vel (scale-velocity (.attenuation cur))]
        (when (> vel 0)
          (.add track (MidiEvent. (ShortMessage. ShortMessage/NOTE_ON chan note vel) time))
          (.add track (MidiEvent. (ShortMessage. ShortMessage/NOTE_OFF chan note 0) end-time)))
        (recur end-time (next data))))))
        
(defn to-sequence
  "Convert a Sound resource to a sequence"
  ([{:keys [channels]} {:keys [instruments panning]}]
   (let [sequence (Sequence. Sequence/SMPTE_30 2)
         insts (or instruments [0 0 0])
         pans  (or panning [96 64 32])]
     (when channels
       (dotimes [chan 3]
         (when (is-audible? (channels chan))
           (let [track (.createTrack sequence)]
             (.add track (MidiEvent.
                          (ShortMessage. ShortMessage/PROGRAM_CHANGE
                                         chan
                                         (insts chan)
                                         0)
                          0))
             (.add track (MidiEvent.
                          (ShortMessage. ShortMessage/CONTROL_CHANGE
                                         chan
                                         10 ; PAN
                                         (pans chan))
                          0))
             (convert-notes track chan (channels chan))))))
     sequence))
  ([game num opts] (to-sequence (res/load-resource game :sound num) opts)))

(defn play-sequence
  [s]
  (doto (MidiSystem/getSequencer) .open (.setSequence s) .start))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
