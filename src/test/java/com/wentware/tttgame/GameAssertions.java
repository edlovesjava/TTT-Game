package com.wentware.tttgame;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameAssertions {
    private GameAssertions() {
        //empty utility class
    }
    public static void assertEntireBoardEqualsValue(String X, Game.Cell[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(X, board[i][j].getValue());
            }
        }
    }
}
