package com.wentware.tttgame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomGameSolverTest {
    RandomGameSolver solver;

    @BeforeEach
    void setup() {
        Game.Cell[][] testBoard = BoardFixture.getTestBoard();
        solver = new RandomGameSolver(testBoard);
    }

    @Test
    void solve() {
        solver.solve();
        assertTrue(Game.isGameOver(solver.getBoard()));
    }

    @Test
    void testComputeNextMoves() {
        Game.Cell[][] testBoard = BoardFixture.getTestBoard();
        var moves = GameSolver.nextMoves(testBoard);
        assertEquals(3, moves.size());
    }

    @Test
    void testPlayRandomUntilGameOver() {
        solver.playRandomUntilGameOver();
        assertTrue(Game.isGameOver(solver.getBoard()));
    }

}