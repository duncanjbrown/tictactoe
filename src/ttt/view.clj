(ns ttt.view)

(defn- render-blanks
  "Given an array of strings and nils, return '_'
  for the nils and return the strings unchanged"
  [strings-and-nils]
  (map #(if (nil? %) "_" %) strings-and-nils))

(defn print-board
  [board]
  (let [rows (map render-blanks board)]
    (println)
    (doall (map #(apply println %) rows))
    (println)))

(defn print-introduction
  [players]
  (println "-----------")
  (println "TIC TAC TOE")
  (println "-----------")
  (println "Players" (apply str (interpose " and " players)) "ready")
  (println "To place a piece, enter a number from 1 to N,")
  (println "Where N is the total number of spaces on the board"))
