(ns ttt.core
  (:require [ttt.game :as game]
            [ttt.view :as view]))

(def size 3)            ;; set the size of the board
(def players ["X" "O"]) ;; list players
(def turns (cycle players))

(defn board []
  "Return a playing board of ttt.core/size"
  (vec (repeat size (vec (repeat size nil)))))

(defn -main
  []
  (view/print-introduction players)
  (let [turns (cycle players)]
    (loop
        [board (board)
         turns turns]
      (if-let [winner-or-draw (game/determine-outcome board players)]
        (do
          (view/print-board board)
          (if (= :draw winner-or-draw)
            (println "A draw!")
            (println winner-or-draw "wins"))
          (println "Goodbye"))
        (let [player (first turns)]
          (do (view/print-board board)
              (println "Player" player "> ")
              (let [next-move (read-string (read-line))
                    new-board (game/place-piece board player next-move)]
                (if (not= new-board board)
                  (recur new-board (rest turns))
                  (do (println "Illegal move")
                      (recur board turns))))))))))
