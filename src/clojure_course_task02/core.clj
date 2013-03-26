(ns clojure-course-task02.core
  (:import java.io.File)
  (:gen-class))

(defn find-files [file-name path]
  (let [pt-func (partial re-find (re-pattern file-name))] 
  (filter #(not-empty (pt-func %))
          (pmap #(.getName %) 
                (file-seq (File. path))))))

(defn usage []
  (println "Usage: $ run.sh file_name path"))

(defn -main [file-name path]
  (if (or (nil? file-name)
          (nil? path))
    (usage)
    (do
      (println "Searching for " file-name " in " path "...")
      (dorun (map println (find-files file-name path))))))
