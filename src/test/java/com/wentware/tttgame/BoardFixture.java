package com.wentware.tttgame;

public class BoardFixture {
    public static Game.Cell[][] getTestBoard() {
        return buildBoard(new String[][]{
                {"X", " ", " "},
                {"O", "X", " "},
                {"X", "O", "O"}
        });
    }

    public static Game.Cell[][] getXIsWinnerColumnBoard() {
        return buildBoard(new String[][]{
                {"X", "O", "O"},
                {"X", "X", " "},
                {"X", "O", "O"}
        });
    }

    public static Game.Cell[][] buildBoard(String[][] boardValues) {
        Game.Cell[][] board = new Game.Cell[3][3];
        for (int i = 0; i < boardValues.length; i++) {
            for (int j = 0; j < boardValues[i].length; j++) {
                board[i][j] = new Game.Cell(boardValues[i][j]);
            }
        }
        return board;

    }
}
