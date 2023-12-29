package com.wentware.tttgame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MinmaxGameSolverTest {
    MinmaxGameSolver solver;

    @BeforeEach
    void setUp() {
        solver = new MinmaxGameSolver();
    }

    @Test
    void testSolve() {
        solver.solve();
        assertTrue(Game.isGameOver(solver.getBoard()));
    }

}