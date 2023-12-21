package com.example.demo2;

public class BoardFixture {
    public static Game.Cell[][] getTestBoard() {
        return new Game.Cell[][]{
                {new Game.Cell(0, 0, "X"), new Game.Cell(0, 1, " "), new Game.Cell(0, 2, "O")},
                {new Game.Cell(1, 0, "O"), new Game.Cell(1, 1, "X"), new Game.Cell(1, 2, " ")},
                {new Game.Cell(2, 0, "X"), new Game.Cell(2, 1, "O"), new Game.Cell(2, 2, "O")}
        };
    }

    public static Game.Cell[][] getXIsWinnerColumnBoard() {
        return new Game.Cell[][]{
                {new Game.Cell(0, 0, "X"), new Game.Cell(0, 1, "O"), new Game.Cell(0, 2, "O")},
                {new Game.Cell(1, 0, "X"), new Game.Cell(1, 1, "X"), new Game.Cell(1, 2, " ")},
                {new Game.Cell(2, 0, "X"), new Game.Cell(2, 1, "O"), new Game.Cell(2, 2, "O")}
        };
    }
}
