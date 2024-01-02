package com.wentware.tttgame;

import java.util.Collections;
import java.util.List;

public class MinmaxGameSolver extends GameSolver {

    MinmaxGameSolver() {
        super();
    }

    public MinmaxGameSolver(Game.Cell[][] board) {
        super(board);
    }

    @Override
    public void solve() {

        Game.Cell[][] currentBoard = board;

        int turn = 1;
        while (!Game.isGameOver(currentBoard)) {
            boolean maximizingPlayer = Game.whichTurn(currentBoard).equals("X");
            var result = minmax(currentBoard, 5, maximizingPlayer);
            currentBoard = result.value();
            System.out.println("Turn " + turn++);
            System.out.println(Game.drawBoard(currentBoard));
        }
        printResult(currentBoard);

        setBoard(currentBoard);

    }

    record Pair<K, V>(K key, V value) {}

    private Pair<Integer, Game.Cell[][]> minmax(Game.Cell[][] board, int depth, boolean maximizingPlayer) {
        //function minimax(node, depth, maximizingPlayer) is
        //    if depth = 0 or node is a terminal node then
        //        return the heuristic value of node
        //    if maximizingPlayer then
        //        value := −∞
        //        for each child of node do
        //            value := max(value, minimax(child, depth − 1, FALSE))
        //        return value
        //    else (* minimizing player *)
        //        value := +∞
        //        for each child of node do
        //            value := min(value, minimax(child, depth − 1, TRUE))
        //        return value
        Game.Cell[][] newBoard = Game.copyBoard(board);

        if (depth == 0 || Game.isGameOver(newBoard)) {
            int score = Game.evaluate(newBoard, maximizingPlayer ? "X" : "O");
            return new Pair<>(score, newBoard);
        }

        if (maximizingPlayer) {
            var bestValue = Integer.MIN_VALUE;
            Game.Cell[][] bestBoard = null;
            List<Game.Cell[][]> cells = nextMoves(newBoard);
            Collections.shuffle(cells);
            for (Game.Cell[][] node : cells) {
                var result = minmax(node, depth - 1, false);
                if (result.key() > bestValue) {
                    bestValue = result.key();
                    bestBoard = node;
                }
            }
            return new Pair<>(bestValue, bestBoard);
        } else {
            var bestValue = Integer.MAX_VALUE;
            Game.Cell[][] bestBoard = null;
            List<Game.Cell[][]> cells = nextMoves(newBoard);
            Collections.shuffle(cells);
            for (Game.Cell[][] node : cells) {
                var result = minmax(node, depth - 1, true);
                if (result.key() < bestValue) {
                    bestValue = result.key();
                    bestBoard = node;
                }
            }
            return new Pair<>(bestValue, bestBoard);
        }
    }
}
