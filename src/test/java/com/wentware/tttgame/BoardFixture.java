package com.wentware.tttgame;

public class BoardFixture {
    public static Game.Cell[][] getTestBoard() {
        return new Game.Cell[][]{
                {new Game.Cell("X"), new Game.Cell(" "), new Game.Cell("O")},
                {new Game.Cell("O"), new Game.Cell("X"), new Game.Cell(" ")},
                {new Game.Cell("X"), new Game.Cell("O"), new Game.Cell("O")}
        };
    }

    public static Game.Cell[][] getXIsWinnerColumnBoard() {
        return new Game.Cell[][]{
                {new Game.Cell("X"), new Game.Cell("O"), new Game.Cell("O")},
                {new Game.Cell("X"), new Game.Cell("X"), new Game.Cell(" ")},
                {new Game.Cell("X"), new Game.Cell("O"), new Game.Cell("O")}
        };
    }
}
