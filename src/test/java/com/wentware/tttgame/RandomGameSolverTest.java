package com.wentware.tttgame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomGameSolverTest {

    Game game;

    RandomGameSolver solver;

    @BeforeEach
    void setup() {
        game = new Game();
        game.setBoard(BoardFixture.getTestBoard());
        solver = new RandomGameSolver(game);
    }

    @Test
    void solve() {
        solver.solve();
        assertTrue(game.isGameOver());
    }
    @Test
    void testComputeAvailableMoves() {
        game.setBoard(BoardFixture.getTestBoard());
        var moves = solver.computeAvailableMoves(game.getBoard());
        assertEquals(2, moves.size());
    }

    @Test
    void testMakeMove() {
        game.setBoard(BoardFixture.getTestBoard());
        var moves = solver.computeAvailableMoves(game.getBoard());
        solver.makeMove(moves.getFirst());
        assertEquals("X", game.getCellAt(0, 0));
    }

    @Test
    void testPlayRandomUntilGameOver() {
        solver.playRandomUntilGameOver();
        assertTrue(game.isGameOver());
    }

}