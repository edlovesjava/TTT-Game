package com.wentware.tttgame;

import org.junit.jupiter.api.BeforeEach;

class MinmaxGameSolverTest {
    Game game;
    MinmaxGameSolver solver;

    @BeforeEach
    void setUp() {
        game = new Game();
        solver = new MinmaxGameSolver(game);
    }

}