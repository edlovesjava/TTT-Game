package com.wentware.tttgame;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public abstract class GameSolver {
    private final Game game;

    protected GameSolver(Game game) {
        this.game = game;
    }

    public void makeMove(PossibleMove possibleMove) {
        game.setCellAt(possibleMove.getX(), possibleMove.getY(), possibleMove.getValue());
    }


    public List<PossibleMove> computeAvailableMoves(Game.Cell[][] board) {
        List<PossibleMove> possibleMoves = new ArrayList<>();
        for (int i = 0; i < getGame().getBoardSize(); i++) {
            for (int j = 0; j < getGame().getBoardSize(); j++) {
                if (board[i][j].getValue().equals(" ")) {
                    possibleMoves.add(new PossibleMove(i, j, getGame().whichTurn(), Collections.emptyList()));
                }
            }
        }
        return possibleMoves;
    }

    public abstract void solve();

}
