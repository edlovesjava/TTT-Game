package com.wentware.tttgame;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Game {

    private Cell[][] board = new Cell[3][3];

    public Game() {
        initBoard();
    }

    public void initBoard() {
        initBoardToValue(" ");
    }

    public int getBoardSize() {
        return board.length;
    }

    public int getHowManyCellsWithValue(String value) {
        int count = 0;
        for (int i = 0; i < getBoardSize(); i++) {
            for (int j = 0; j < getBoardSize(); j++) {
                if (board[i][j].getValue().equals(value)) {
                    count++;
                }
            }
        }
        return count;
    }

    public void initBoardToValue(String value) {
        for (int i = 0; i < getBoardSize(); i++) {
            for (int j = 0; j < getBoardSize(); j++) {
                board[i][j] = new Cell(value);
            }
        }
    }

    public void setCellAt(int x, int y, String value) {
        board[x][y].setValue(value);
    }


    public String getCellAt(int x, int y) {
        return board[x][y].getValue();
    }

    public boolean hasWinner(String value) {
        //find a row with 3 X's or 3 O's
        for (int i = 0; i < getBoardSize(); i++) {
            if (board[i][0].getValue().equals(value) &&
                    board[i][1].getValue().equals(value) &&
                    board[i][2].getValue().equals(value)) {
                return true;
            }
        }
        //find a column with 3 X's or 3 O's
        for (int i = 0; i < getBoardSize(); i++) {
            if (board[0][i].getValue().equals(value) &&
                    board[1][i].getValue().equals(value) &&
                    board[2][i].getValue().equals(value)) {
                return true;
            }
        }
        //find a diagonal with 3 X's or 3 O's
        if (board[0][0].getValue().equals(value) &&
                board[1][1].getValue().equals(value) &&
                board[2][2].getValue().equals(value)) {
            return true;
        }
        return board[0][2].getValue().equals(value) &&
                board[1][1].getValue().equals(value) &&
                board[2][0].getValue().equals(value);
    }

    public boolean isBoardFull() {
        return getHowManyCellsWithValue(" ") == 0;
    }

    public boolean isGameOver() {
        return hasWinner("X") || hasWinner("O") || isBoardFull();
    }

    public String getWinner() {
        if (hasWinner("X")) {
            return "X";
        }
        if (hasWinner("O")) {
            return "O";
        }
        return " ";
    }

    public String whichTurn() {
        if (getHowManyCellsWithValue("X") == getHowManyCellsWithValue("O")) {
            return "X";
        } else {
            return "O";
        }
    }

    public String drawBoard() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getBoardSize(); i++) {
            for (int j = 0; j < getBoardSize(); j++) {
                sb.append(" ").append(drawCell(i, j)).append(" ");
                if (j < getBoardSize() - 1) {
                    sb.append("|");
                }
            }
            if (i < getBoardSize() - 1) {
                sb.append("\n---+---+---\n");
            }
        }
        return sb.toString();
    }

    private String drawCell(int i, int j) {
        return (board[i][j].isEmpty() ? "." : board[i][j].getValue());
    }


    @Getter
    @Setter
    public static class Cell {

        private String value;

        public Cell(String value) {
            this.value = value;
        }

        public boolean isEmpty() {
            return value.equals(" ");
        }
    }

}
