package com.wentware.tttgame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.Random;

public class RandomGameSolver extends GameSolver {
    private final Logger logger = LoggerFactory.getLogger(RandomGameSolver.class);

    private final Random random = new Random();

    public RandomGameSolver(Game game) {
        super(game);
    }

    @Override
    public void solve() {
        playRandomUntilGameOver();
    }


    public void playRandom() {
        if (getGame().isGameOver()) {
            return;
        }
        //try until we find an empty cell
        while(true) {
            int x = random.nextInt(getGame().getBoardSize());
            int y = random.nextInt(getGame().getBoardSize());
            if (getGame().getCellAt(x, y).equals(" ")) {
                getGame().setCellAt(x, y, getGame().whichTurn());
                break;
            }
        }

    }

    private void printResult() {
        if (getGame().isGameOver()) {
            logger.info("Game is over {}", (Objects.equals(getGame().getWinner(), " ") ? " - tie" : " - " + getGame().getWinner() + " wins"));
        }
    }

    public void playRandomUntilGameOver() {
        var turn = 0;
        while (!getGame().isGameOver()) {
            logger.info("turn: {}", turn++);
            playRandom();

        }
        printResult();
    }

}
