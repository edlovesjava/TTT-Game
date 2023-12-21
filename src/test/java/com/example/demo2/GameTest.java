package com.example.demo2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.example.demo2.GameAssertions.*;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    Game game = new Game();


    @BeforeEach
    void setup() {
        game.initBoard();
    }

    @Test
    void testInit() {
        assertEntireBoardEqualsValue(" ", game);
    }

    @Test
    void testSetCells() {
        game.setBoard(BoardFixture.getTestBoard());
        assertEquals("X", game.getCellAt(0, 0));
    }

    @Test
    void testHowManyCellsWithValue() {
        game.setBoard(BoardFixture.getTestBoard());
        assertEquals(3, game.getHowManyCellsWithValue("X"));
    }

    @Test
    void testSetCellAt() {
        game.setCellAt(
                0, 0, "X"
        );
        assertEquals("X", game.getCellAt(0, 0));
    }

    @Test
    void testGetCellAt() {
        game.getCellAt(0, 0);
        assertEquals(" ", game.getCellAt(0, 0));
    }

    @Test
    void testNoWinner() {
        game.setBoard(BoardFixture.getTestBoard());
        assertFalse(game.hasWinner("X"));
        assertFalse(game.hasWinner("O"));

    }

    @Test
    void testHasWinner() {
        game.setBoard(BoardFixture.getXIsWinnerColumnBoard());
        assertTrue(game.hasWinner("X"));
        assertFalse(game.hasWinner("O"));
    }

    @Test
    void testIsBoardFull() {
        game.setBoard(BoardFixture.getTestBoard());
        assertFalse(game.isBoardFull());
    }

    @Test
    void testIsGameOver() {
        game.setBoard(BoardFixture.getTestBoard());
        assertFalse(game.isGameOver());
    }

    @Test
    void testIsGameOverYes() {
        game.setBoard(BoardFixture.getXIsWinnerColumnBoard());
        assertTrue(game.isGameOver());
    }

    @Test
    void testGetWinner() {
        game.setBoard(BoardFixture.getXIsWinnerColumnBoard());
        assertEquals("X", game.getWinner());
    }

    @Test
    void testComputePossibleMovesForDepth1() {
        game.setBoard(BoardFixture.getTestBoard());
        var moves = game.computePossibleMoves(game.getBoard(), 1);
        assertEquals(8, moves.size());
    }

    @Test
    void testComputePossibleMovesForDepth2() {
        game.setBoard(BoardFixture.getTestBoard());
        var moves = game.computePossibleMoves(game.getBoard(),2);
        assertEquals(8 * 7, moves.size());
    }

    @Test
    void testPlayRandomUntilGameOver() {
        game.playRandomUntilGameOver();
        game.printBoard();
        assertTrue(game.isGameOver());
    }
}