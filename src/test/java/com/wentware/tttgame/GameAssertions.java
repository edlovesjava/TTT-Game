package com.wentware.tttgame;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameAssertions {
    private GameAssertions() {
        //empty utility class
    }
    public static void assertEntireBoardEqualsValue(String X, Game game) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(X, game.getCellAt(i, j));
            }
        }
    }
}
