(ns org.rwtodd.agi-to-midi.core
  (:require [org.rwtodd.agi-game.resources :as res]
            [clojure.java.io :as io])
  (:import (javax.sound.midi Sequence        Track
                             MidiEvent       ShortMessage
                             MidiSystem))
  (:gen-class))

(defn is-audible?
  "Determines if a track has any audible notes"
  [data]
  (some #(< (.attenuation %) 15) data))

(defn scale-velocity
  "AGI sound resources have an attenuation from 0 (highest-volume)
  to 15 (silent).  This function adjusts the scale to the 0-127
  MIDI scale."
  [atten]
  (Math/round (double (* (- 15 atten) (/ 127 15)))))

(def freq-factor (Math/log10 (Math/pow 2.0 (double (/ 1 12)))))

(defn scale-frequency
  "AGI Sound resources store a frequency divisor specific to the
  TI Sound chip in the PCjr.  This function converts it to the
  nearest MIDI note number."
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
        
(defn to-midi
  "Convert a Sound resource to a MIDI sequence. You can pass an
  options map to this function: :instruments is a vector of general-midi
  instrument numbers, and :panning is a vector of MIDI pan data (0-127 left-to-right).

  You can either pass a loaded sound resource, or request it by :key and
  resource number.  e.g.: (to-midi :kq3 20 {:instruments [ 7 8 9 ]})
  "
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
  ([game num opts] (to-midi (res/load-resource game :sound num) opts)))

(defn play-midi
  "Helper function to send a sequence to the default sequencer."
  [midi]
  (doto (MidiSystem/getSequencer) .open (.setSequence midi) .start))

(defn write-midi
  "Helper function to write a midi sequence to a file."
  [midi fname]
  (MidiSystem/write midi 1 (io/as-file fname)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
