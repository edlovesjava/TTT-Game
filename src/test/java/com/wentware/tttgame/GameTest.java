package com.wentware.tttgame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.wentware.tttgame.GameAssertions.assertEntireBoardEqualsValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameTest {
    @BeforeEach
    void setup() {
    }


    @Test
    void testInit() {
        Game.Cell[][] testBoard = Game.initBoard();
        assertEntireBoardEqualsValue(" ", testBoard);
    }


    @Test
    void testEvaluateNotWinButXHas2() {
        Game.Cell[][] testBoard = BoardFixture.getTestBoard();
        assertEquals(5, Game.evaluate(testBoard, "X"));
        assertEquals(0, Game.evaluate(testBoard, "O"));
    }

    @Test
    void testEvaluateWinnerBoardByCol() {
        Game.Cell[][] xIsWinnerColumnBoard = BoardFixture.getXIsWinnerColumnBoard();
        assertEquals(10, Game.evaluate(xIsWinnerColumnBoard, "X"));
        assertEquals(-5, Game.evaluate(xIsWinnerColumnBoard, "O"));
    }

    @Test
    void testEvaluateWinnerBoardByRow() {
        Game.Cell[][] board = BoardFixture.buildBoard(new String[][]{
                {"X", "X", " "},
                {"O", "X", "X"},
                {"O", "O", "O"}
        });
        assertEquals(5, Game.evaluate(board, "X"));
        assertEquals(-10, Game.evaluate(board, "O"));
    }

    @Test
    void testEvaluateWinnerBoardByDiagonal() {
        Game.Cell[][] board = BoardFixture.buildBoard(new String[][]{
                {"X", "X", "O"},
                {"O", "X", " "},
                {"O", " ", "X"}
        });
        assertEquals(10, Game.evaluate(board, "X"));
        assertEquals(0, Game.evaluate(board, "O"));
    }

    @Test
    void testEvaluateHas2InRow() {
        Game.Cell[][] board = BoardFixture.buildBoard(new String[][]{
                {" ", "X", "X"},
                {"O", "X", " "},
                {"O", " ", " "}
        });
        assertEquals(5, Game.evaluate(board, "X"));
        assertEquals(-5, Game.evaluate(board, "O"));
    }


    @Test
    void testIsBoardFull() {
        Game.Cell[][] testBoard = BoardFixture.getTestBoard();
        assertFalse(Game.isBoardFull(testBoard));
    }

    @Test
    void testHowManyCellsWithValue() {
        Game.Cell[][] testBoard = BoardFixture.getTestBoard();
        assertEquals(3, Game.getHowManyCellsWithValue(testBoard, "X"));
        assertEquals(3, Game.getHowManyCellsWithValue(testBoard, "O"));
    }

    @Test
    void testWhichTurn() {
        Game.Cell[][] testBoard = BoardFixture.getTestBoard();
        assertEquals("X", Game.whichTurn(testBoard));
    }

    @Test
    void testNoWinner() {
        Game.Cell[][] testBoard = BoardFixture.getTestBoard();
        assertFalse(Game.hasWinner(testBoard, "X"));
        assertFalse(Game.hasWinner(testBoard, "O"));
    }

    @Test
    void testHasWinner() {
        Game.Cell[][] xIsWinnerColumnBoard = BoardFixture.getXIsWinnerColumnBoard();
        assertTrue(Game.hasWinner(xIsWinnerColumnBoard, "X"));
        assertFalse(Game.hasWinner(xIsWinnerColumnBoard, "O"));
    }

    @Test
    void testIsGameOverNo() {
        Game.Cell[][] testBoard = BoardFixture.getTestBoard();
        assertFalse(Game.isGameOver(testBoard));
    }

    @Test
    void testIsGameOverYes() {
        Game.Cell[][] xIsWinnerColumnBoard = BoardFixture.getXIsWinnerColumnBoard();
        assertTrue(Game.isGameOver(xIsWinnerColumnBoard));
    }

    @Test
    void testIsGameOverTie() {
        Game.Cell[][] board = BoardFixture.buildBoard(new String[][]{
                {"O", "X", "O"},
                {"O", "O", "X"},
                {"X", "O", "X"}
        });
        assertTrue(Game.isGameOver(board));
    }

    @Test
    void testGetWinner() {
        Game.Cell[][] xIsWinnerColumnBoard = BoardFixture.getXIsWinnerColumnBoard();
        assertEquals("X", Game.getWinner(xIsWinnerColumnBoard));
    }
}
