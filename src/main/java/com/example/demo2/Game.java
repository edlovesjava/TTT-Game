package com.example.demo2;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Game {
    Random random = new Random();
    private final Logger logger = LoggerFactory.getLogger(Game.class);
    @Getter
    @Setter
    private Cell[][] board = new Cell[3][3];

    public void initBoard() {
        initBoardToValue(" ");
    }

    public int getBoardSize() {
        return board.length;
    }

    public int getHowManyCellsWithValue(String value) {
        int count = 0;
        for (int i = 0; i < getBoardSize(); i++) {
            for (int j = 0; j < getBoardSize(); j++) {
                if (board[i][j].getValue().equals(value)) {
                    count++;
                }
            }
        }
        return count;
    }

    public void initBoardToValue(String value) {
        for (int i = 0; i < getBoardSize(); i++) {
            for (int j = 0; j < getBoardSize(); j++) {
                board[i][j] = new Cell(i, j, value);
            }
        }
    }

    public void setCellAt(int x, int y, String value) {
        board[x][y].setValue(value);
    }


    public String getCellAt(int x, int y) {
        return board[x][y].getValue();
    }

    public boolean hasWinner(String value) {
        //find a row with 3 X's or 3 O's
        for (int i = 0; i < getBoardSize(); i++) {
            if (board[i][0].getValue().equals(value) &&
                    board[i][1].getValue().equals(value) &&
                    board[i][2].getValue().equals(value)) {
                return true;
            }
        }
        //find a column with 3 X's or 3 O's
        for (int i = 0; i < getBoardSize(); i++) {
            if (board[0][i].getValue().equals(value) &&
                    board[1][i].getValue().equals(value) &&
                    board[2][i].getValue().equals(value)) {
                return true;
            }
        }
        //find a diagonal with 3 X's or 3 O's
        if (board[0][0].getValue().equals(value) &&
                board[1][1].getValue().equals(value) &&
                board[2][2].getValue().equals(value)) {
            return true;
        }
        return board[0][2].getValue().equals(value) &&
                board[1][1].getValue().equals(value) &&
                board[2][0].getValue().equals(value);
    }

    public boolean isBoardFull() {
        return getHowManyCellsWithValue(" ") == 0;
    }

    public boolean isGameOver() {
        return hasWinner("X") || hasWinner("O") || isBoardFull();
    }

    public String getWinner() {
        if (hasWinner("X")) {
            return "X";
        }
        if (hasWinner("O")) {
            return "O";
        }
        return " ";
    }

    public void printBoard() {
        logger.info("Board:");
        for (int i = 0; i < getBoardSize(); i++) {
            StringBuilder row = new StringBuilder();
            for (int j = 0; j < getBoardSize(); j++) {
                row.append(board[i][j].getValue().equals(" ") ? "_" : board[i][j].getValue());
            }
            logger.info(row.toString());
        }
    }

    public String whosTurn() {
        if (getHowManyCellsWithValue("X") == getHowManyCellsWithValue("O")) {
            return "X";
        } else {
            return "O";
        }
    }

    public void play(int x, int y) {
        if (isGameOver()) {
            logger.info("Game is over");
            return;
        }
        if (!getCellAt(x, y).equals(" ")) {
            logger.info("Cell is already taken");
            return;
        }
        setCellAt(x, y, whosTurn());
    }

    public void playRandom() {
        if (isGameOver()) {
            return;
        }
        //try until we find an empty cell
        while(true) {
            int x = random.nextInt(getBoardSize());
            int y = random.nextInt(getBoardSize());
            if (getCellAt(x, y).equals(" ")) {
                setCellAt(x, y, whosTurn());
                break;
            }
        }

    }

    private void printResult() {
        if (isGameOver()) {
            logger.info("Game is over {}", (Objects.equals(getWinner(), " ") ? " - tie" : " - " + getWinner() + " wins"));
        }
    }

    public void playRandomUntilGameOver() {
        int turn = 0;
        while (!isGameOver()) {
            logger.info("turn: {}", turn++);
            printBoard();
            playRandom();

        }
        printResult();
    }

    //create graph of all possible moves of the game
    @Getter
    public static class PossibleMove {
        private float heuristic;
        private final int x;
        private final int y;
        private final String value;
        private final List<PossibleMove> nextMoves;
        public PossibleMove(int x, int y, String value, List<PossibleMove> nextMoves) {
            this.x = x;
            this.y = y;
            this.value = value;
            this.nextMoves = nextMoves;
        }
    }

    public List<PossibleMove> computePossibleMoves(Cell[][] fromBoard, int depth) {
        Game game = new Game();
        game.setBoard(fromBoard);

        if (depth == 0 || game.isGameOver()) {
            return Collections.emptyList();
        }
        var possibleMoves = new ArrayList<PossibleMove>();

        for (int i = 0; i < getBoardSize(); i++) {
            for (int j = 0; j < getBoardSize(); j++) {
                if (getCellAt(i, j).equals(" ")) {

                    game.setCellAt(i, j, game.whosTurn());
                    possibleMoves.add(new PossibleMove(i, j, game.whosTurn(), game.computePossibleMoves(game.getBoard(), depth - 1)));
                }
            }
        }
        return possibleMoves;
    }

    public float computeHeuristic(PossibleMove possibleMove) {
        if (possibleMove.getNextMoves().isEmpty()) {
            if (hasWinner("X")) {
                return 1;
            }
            if (hasWinner("O")) {
                return -1;
            }
            return 0;
        }
        float sum = 0;
        for (PossibleMove nextMove : possibleMove.getNextMoves()) {
            sum += computeHeuristic(nextMove);
        }
        return sum / possibleMove.getNextMoves().size();
    }



    public void playBestMove() {
        List<PossibleMove> possibleMoves = computePossibleMoves(getBoard(), 2);
        PossibleMove bestMove = null;
        float bestHeuristic = -1;
        for (PossibleMove possibleMove : possibleMoves) {
            float heuristic = computeHeuristic(possibleMove);
            if (heuristic > bestHeuristic) {
                bestHeuristic = heuristic;
                bestMove = possibleMove;
            }
        }
        if (bestMove != null) {
            setCellAt(bestMove.getX(), bestMove.getY(), whosTurn());
        }
    }

    @Getter
    public static class Cell {
        private final int x;
        private final int y;
        @Setter
        private String value;

        public Cell(int x, int y, String value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }

    }
}
