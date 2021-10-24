(ns org-rwtodd.agi.resources
  (:require [clojure.java.io :as io]))


;; ====== Validation and Utilties
(defn game-path?
  "Verify that this appears to be an AGI game directory"
  [path]
  (let [dir (io/file path)]
    (and (.isDirectory dir)
         (.exists (io/file dir "AGIDATA.OVL")))))

(defn- read-entire-file
  "Reads an entire file into a byte array"
  [path fname]
  (with-open [stream (->> fname (io/file path) io/input-stream)]
    (.readAllBytes stream)))

;; ====== Game Version Info
;; ---- support functions for `extract-version` -------------
(defn- digit? [x] (<= 48 x 57)) ;; 48-57 == ASCII 0-9
(defn- dot?   [x] (== x 46))    ;; 46 == ASCII '.'
(def ^:private  long-vernum [digit? dot? digit? digit? digit? dot? digit? digit? digit?])
(def ^:private short-vernum  [digit? dot? digit? digit? digit?])
(defn- vermatch [ver bs]  (every? true? (map #(%1 %2) ver bs)))

(defn extract-version
  "Extract the AGI version data from the provided seq of bytes, BS. Typically
   this data will come from the AGIDATA.OVL file"
  [bs]
  (let [;; get the first tail of the input which matches a short version number
        match (first (filter #(vermatch short-vernum %)
                             (reductions (fn [s _] (rest s)) bs bs)))
        
        ;; attempt to match the longer version, and take 9 chars
        ;; if successful.  Otherwise, take the short version we
        ;; already matched
        strver (apply str
                      (sequence (comp (take (if (vermatch long-vernum match) 9 5))
                                      (map unchecked-char))
                                match))]
    ;; make sure the resulting string is one of the valid lengths
    (case (count strver)
      (5 9)   strver
      (6 7 8) (.substring strver 0 5)
      nil)))

(defn numeric-version
  "Convert an AGI version string to a double. It is expected to be either
  d.ddd or d.ddd.ddd and any other format will cuase an exception."
  [sv]
  (Double/parseDouble (if (== (count sv) 9)
                        (str (.substring sv 0 5) (.substring sv 6))
                        sv)))

(defn load-version
  "Determine the game's AGI version based on the AGIDATA.OVL file."
  [path]
  (-> (read-entire-file path "AGIDATA.OVL") extract-version numeric-version))

(defn determine-v3-prefix
  "Version 3 AGI games have a prefix on their resource files, and we can
  figure out what it is by inspecting the file system.  Needs the PATH to
  the root directory of the game."
  [path]
  (let [fls    (file-seq (io/file path))
        volf   (first (filter #(.endsWith (.getName %) "VOL.0") fls))
        volstr (and volf (.getName volf))
        pfx    (and volf (.substring volstr 0 (- (.length volstr) 5)))]
    (if (.exists (io/file path (str pfx "DIR")))
      pfx
      (throw (ex-info "Can't determine game prefix!" {:dir path})))))

;; ====== Resource Directories
(defn- split-v3-dir
  "Split a version-3 resource directory into sound, view, pic, and logic portions."
  [dirfl]
  { :sounds nil :logics nil :views nil :pics nil })

(def ^:private v2-directories
  { :logics "LOGDIR", :sounds "SNDDIR", :views "VIEWDIR", :pics "PICDIR" })

(defn load-resource-directories
  "Returns a map of :sound, :view, :pic, and :logic resource directories from the
  game's directory in PATH.  How this is done depends no the game version.  For V3
  directories, you must pass the game's PREFIX."
  ([path]
   (zipmap (keys v2-directories)
           (map #(read-entire-file path %) (vals v2-directories))))
  ([path prefix]
   (split-v3-dir (read-entire-file path (str prefix "DIR")))))

;; ====== High-Level Interface

(defn load-game-info
  "This is the main high-level function in this package, which verifies the
  given PATH points to an AGI game, and loads version and resource directory
  information into a map.  This map will be used by the other functions to
  load resources."
  [path]
  (if (game-path? path)
    (let [ver (load-version path)
          pfx (when (>= ver 3.0) (determine-v3-prefix path))]
      (merge { :version ver, :path (io/file path) }
             (if pfx
               (load-resource-directories path pfx)
               (load-resource-directories path))
             (and pfx { :prefix pfx })))

    (throw (ex-info "Not a game directory!" {:dir path}))))


;; ====== end of file
;; Local Variables:
;; page-delimiter: "^;; ======"
;; End:
