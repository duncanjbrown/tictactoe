# ttt

Tic tac toe, following a session at London Clojurians' monthly Clojure Dojo. The original repository from the Dojo is [here](https://github.com/ldnclj/tictactoe).

![Screenshot](https://user-images.githubusercontent.com/642279/42116654-1e7ccb8e-7bf0-11e8-8440-5eed4a08116d.png)

## Instructions

`lein run`

To place a piece, enter a number between 1 and 9.

You can increase the size of the board by adjusting the `size` var in `core`.
You can change the players by editing the `players` var in the same file.

## Known issues

- It's impossible to quit, save via C-c.
- You can crash it by typing non-numeric chars in at the prompt.
