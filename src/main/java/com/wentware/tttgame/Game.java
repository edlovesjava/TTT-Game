package com.wentware.tttgame;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Game {
    private Game() {
        //static methods only
    }

    public static Game.Cell[][] initBoard() {
        Game.Cell[][] board = new Game.Cell[3][3];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = new Cell(" ");
            }
        }
        return board;
    }

    public static int getHowManyCellsWithValue(Cell[][] board, String value) {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j].getValue().equals(value)) {
                    count++;
                }
            }
        }
        return count;
    }

    public static boolean hasWinner(Game.Cell[][] board, String value) {
        //find a row with 3 X's or 3 O's
        for (int i = 0; i < board.length; i++) {
            if (board[i][0].getValue().equals(value) &&
                    board[i][1].getValue().equals(value) &&
                    board[i][2].getValue().equals(value)) {
                return true;
            }
        }
        //find a column with 3 X's or 3 O's
        for (int i = 0; i < board.length; i++) {
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

    public static boolean isBoardFull(Cell[][] board) {
        return getHowManyCellsWithValue(board," ") == 0;
    }

    public static boolean isGameOver(Cell[][] board) {
        return hasWinner(board, "X") || hasWinner(board,"O") || isBoardFull(board);
    }

    public static String getWinner(Cell[][] board) {
        if (hasWinner(board,"X")) {
            return "X";
        }
        if (hasWinner(board,"O")) {
            return "O";
        }
        return " ";
    }

    public static String whichTurn(Cell[][] board) {
        if (getHowManyCellsWithValue(board,"X") == getHowManyCellsWithValue(board,"O")) {
            return "X";
        } else {
            return "O";
        }
    }

    public static String drawBoard(Cell[][] board) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                sb.append(" ").append((board[i][j].isEmpty() ? "." : board[i][j].getValue())).append(" ");
                if (j < board.length - 1) {
                    sb.append("|");
                }
            }
            if (i < board.length - 1) {
                sb.append("\n---+---+---\n");
            }
        }
        return sb.toString();
    }

    public static int evaluate(Game.Cell[][] board, String player) {
        int isMaximizer = player.equals("X") ? 1 : -1;
        if (hasWinner(board, player)) {
            return 10 * isMaximizer;
        }
        if (hasTwoInARow(board, player)) {
            return 5 * isMaximizer;
        }
        if (hasTwoInAColumn(board, player)) {
            return 5 * isMaximizer;
        }
        if(hasTwoInADiagonal(board, player)) {
            return 5 * isMaximizer;
        }
        return 0;
    }

    private static boolean hasTwoInARow(Game.Cell[][] board, String player) {
        //find a row with 2 X's or 2 O's and an empty cell
        return find2MatchingAndSpace(board, player, false);
    }

    private static boolean hasTwoInAColumn(Cell[][] board, String player) {
        //find a col with 2 X's or 2 O's and an empty cell
        return find2MatchingAndSpace(board, player, true);
    }

    public static boolean find2MatchingAndSpace(Game.Cell[][] board, String player, boolean rotation) {
        for (int i = 0; i < board.length; i++) {
            for(int emptyPos = 0; emptyPos < board.length; emptyPos++) { //emptyPos is the space
                int found = 0;
                for(int j = 0; j < board.length; j++) {
                    String testValue = j == emptyPos ? " " : player;
                    //not a match
                    if (!getBoardValue(board, i, j, rotation).equals(testValue)) {
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

    private static String getBoardValue(Cell[][] board, int i, int j, boolean rotation) {
        int row = i;
        int col = j;
        if (rotation) {
            row = j; col = i;
        }
        return board[row][col].getValue();
    }

    private static boolean hasTwoInADiagonal(Cell[][] board, String player) {
        //find a diagonal with 2 X's or 2 O's and an empty cell
        //diagonal from top left to bottom right
        for (int emptyPos = 0; emptyPos < board.length; emptyPos++) { //emptyPos is the space
            int found = 0;
            for (var i = 0; i < board.length; i++) {
                String testValue = i == emptyPos ? " " : player;
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
        for (int emptyPos = 0; emptyPos < board.length; emptyPos++) { //emptyPos is the space
            int found = 0;
            for (int i = 0; i < board.length; i++) {
                String testValue = i == emptyPos ? " " : player;
                if (!board[i][board.length - 1 - i].getValue().equals(testValue)) {
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

    public static Cell[][] copyBoard(Cell[][] board) {
        Cell[][] newBoard = new Cell[3][3];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                newBoard[i][j] = new Cell(board[i][j].getValue());
            }
        }
        return newBoard;
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
