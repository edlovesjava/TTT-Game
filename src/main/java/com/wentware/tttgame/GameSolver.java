package com.wentware.tttgame;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public abstract class GameSolver {

    Game.Cell[][] board;

    GameSolver() {
        board = Game.initBoard();
    }

    GameSolver(Game.Cell[][] board) {
        setBoard(board);
    }

    public static List<Game.Cell[][]> nextMoves(Game.Cell[][] board) {

        List<Game.Cell[][]> possibleMoves = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j].getValue().equals(" ")) {
                    Game.Cell[][] newBoard = Game.copyBoard(board);
                    newBoard[i][j].setValue(Game.whichTurn(newBoard));
                    possibleMoves.add(newBoard);
                }
            }
        }
        return possibleMoves;
    }


    public abstract void solve();

    protected void printResult(Game.Cell[][] board) {
        if (Game.isGameOver(board)) {
            System.out.println("Game is over " + (Objects.equals(Game.getWinner(board), " ") ? " - tie" : " - " + Game.getWinner(board) + " wins"));
        }
    }
}
