(ns backpolish.core (:gen-class))

(use '[backpolish.lib :only (calc)])

(defn -main [& args]
  (println (calc args)))

