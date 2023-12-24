package com.wentware.tttgame;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public abstract class GameSolver {
    protected final Logger logger = LoggerFactory.getLogger(GameSolver.class);
    private final Game game;

    protected GameSolver(Game game) {
        this.game = game;
    }

    public void makeMove(PossibleMove possibleMove) {
        game.setCellAt(possibleMove.x(), possibleMove.y(), possibleMove.value());
    }


    public List<PossibleMove> computeAvailableMoves(Game.Cell[][] board) {
        List<PossibleMove> possibleMoves = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j].getValue().equals(" ")) {
                    possibleMoves.add(new PossibleMove(i, j, getGame().whichTurn()));
                }
            }
        }
        return possibleMoves;
    }

    public abstract void solve();

    protected void printResult() {
        if (getGame().isGameOver()) {
            logger.info("Game is over {}", (Objects.equals(getGame().getWinner(), " ") ? " - tie" : " - " + getGame().getWinner() + " wins"));
        }
    }

    //create graph of all possible moves of the game
        public record PossibleMove(int x, int y, String value) {
    }
}
