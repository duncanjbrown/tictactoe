(ns ttt.game)

(defn- winning-runs
  "Given a seq of n-element seqs, return those which
  are entirely filled with the given symbol"
  [runs symbol]
  (filter #(apply = symbol %) runs))

(defn- horizontal-winner?
  [board player]
  (let [winners (winning-runs board player)]
    (some? (seq winners))))

(defn- vertical-winner?
  [board player]
  (let [cols (apply map vector board)] ;; rotate the board 90 degrees
    (horizontal-winner? cols player)))

(defn- diagonal-winner?
  [board player]
  (let [size (count (first board))
        diag-1 (apply map vector [(range size) (range size)])
        diag-2 (apply map vector [(reverse (range size)) (range size)])
        d1 (map #(get-in board %) diag-1)
        d2 (map #(get-in board %) diag-2)]
    (some? (seq (winning-runs [d1 d2] player)))))

(defn- any-winners?
  [board player]
  (or (diagonal-winner? board player)
      (vertical-winner? board player)
      (horizontal-winner? board player)))

(defn- full?
  [board]
  (every? #(not= nil %) (flatten board)))

(defn- integer-to-coords
  "Given a matrix, return the coordinates of the
  nth slot"
  [matrix n]
  (let [matrix-row-length (count (first matrix))]
    ((juxt quot rem) n matrix-row-length)))

(defn place-piece
  "Given a board, a piece, and a integer index into the board from 1
  to board-max, try to place the piece in the board. Return a new board,
  or the original board if it couldn't be placed"
  [board piece place]
  (let [board-max (count (flatten board))
        zero-indexed-place (dec place)
        position (integer-to-coords board zero-indexed-place)]
    (if (and (< zero-indexed-place board-max)
             (= nil (get-in board position)))
      (assoc-in board position piece)
      board)))

(defn determine-outcome
  "Given a board and players, report a win or a draw"
  [board players]
  (if-let [winner (seq (filter (partial any-winners? board) players))]
    (first winner)
    (if (full? board)
      :draw
      nil)))
