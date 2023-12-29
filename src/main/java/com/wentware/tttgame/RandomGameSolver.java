package com.wentware.tttgame;

import java.util.Random;

public class RandomGameSolver extends GameSolver {

    private final Random random = new Random();
    RandomGameSolver() {
        super();
    }

    RandomGameSolver(Game.Cell[][] board) {
        super(board);
    }

    @Override
    public void solve() {
        playRandomUntilGameOver();
    }


    public void playRandom() {
        if (Game.isGameOver(getBoard())) {
            return;
        }
        //try until we find an empty cell
        while(true) {
            int x = random.nextInt(board.length);
            int y = random.nextInt(board.length);
            if (board[x][y].getValue().equals(" ")) {
                board[x][y].setValue(Game.whichTurn(board));
                break;
            }
        }

    }

    public void playRandomUntilGameOver() {
        var turn = 0;
        while (!Game.isGameOver(board)) {
            System.out.println("turn: " + turn++);
            playRandom();

        }
        printResult(board);
    }

}
