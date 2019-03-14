(defproject org.rwtodd/agi-to-midi "0.1.0-SNAPSHOT"
  :description "Converts AGI Sound resources to MIDI"
  :url "https://github.com/rwtodd"
  :license {:name "GPL"
            :url "https://gnu.org/licenses/gpl.html"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.rwtodd/agi-game "0.1.0-SNAPSHOT"]]
  :main ^:skip-aot org.rwtodd.agi-to-midi.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
