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
                sb.append(" ").append((board[i][j].isEmpty() ? "." : board[i][j].getValue())).append(" ");
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

    public int evaluate(String player) {
        int isMaximizer = player.equals("X") ? 1 : -1;
        if (hasWinner(player)) {
            return 10 * isMaximizer;
        }
        if (hasTwoInARow(player)) {
            return 5 * isMaximizer;
        }
        if (hasTwoInAColumn(player)) {
            return 5 * isMaximizer;
        }
        if(hasTwoInADiagonal(player)) {
            return 5 * isMaximizer;
        }
        return 0;
    }

    private boolean hasTwoInARow(String player) {
        //find a row with 2 X's or 2 O's and an empty cell
        return find2MatchingAndSpace(player, false);
    }

    private boolean find2MatchingAndSpace(String player, boolean rotation) {
        for (int i = 0; i < getBoardSize(); i++) {
            for(int s = 0; s < getBoardSize(); s++) { //s is the space
                int found = 0;
                for(int j = 0; j < getBoardSize(); j++) {
                    String testValue = j == s ? " " : player;
                    //not a match
                    int row = i;
                    int col = j;
                    if (rotation) {
                        row = j; col = i;
                    }
                    if (!board[row][col].getValue().equals(testValue)) {
                        break; //not a match
                    }
                    found++;
                }
                if (found == 3) { //found matched
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasTwoInAColumn(String player) {
        //find a col with 2 X's or 2 O's and an empty cell
        return find2MatchingAndSpace(player, true);
    }

    private boolean hasTwoInADiagonal(String player) {
        //find a diagonal with 2 X's or 2 O's and an empty cell
        //diagonal from top left to bottom right
        for (int s = 0; s < getBoardSize(); s++) { //s is the space
            int found = 0;
            for (int i = 0; i < getBoardSize(); i++) {
                String testValue = i == s ? " " : player;
                if (!board[i][i].getValue().equals(testValue)) {
                    break; //not a match
                }
                found++;
            }
            if (found == 3) { //found matched
                return true;
            }
        }

        //diagonal from top right to bottom left
        for (int s = 0; s < getBoardSize(); s++) { //s is the space
            int found = 0;
            for (int i = 0; i < getBoardSize(); i++) {
                String testValue = i == s ? " " : player;
                if (!board[i][getBoardSize() - 1 - i].getValue().equals(testValue)) {
                    break; //not a match
                }
                found++;
            }
            if (found == 3) { //found matched
                return true;
            }
        }
        return false;
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
