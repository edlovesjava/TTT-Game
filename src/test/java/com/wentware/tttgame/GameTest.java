package com.wentware.tttgame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.wentware.tttgame.GameAssertions.*;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Game game;

    @BeforeEach
    void setup() {
        game = new Game();
    }

    @Nested()
    @DisplayName("Test the board Cell methods")
    class TestBoardCells {
        @Test
        void testInit() {
            assertEntireBoardEqualsValue(" ", game);
        }

        @Test
        void testSetCells() {
            game.setBoard(BoardFixture.getTestBoard());
            assertEquals("X", game.getCellAt(0, 0));
        }

        @Test
        void testSetCellAt() {
            game.setCellAt(
                    0, 0, "X"
            );
            assertEquals("X", game.getCellAt(0, 0));
        }

        @Test
        void testGetCellAt() {
            game.getCellAt(0, 0);
            assertEquals(" ", game.getCellAt(0, 0));
        }
    }

    @Nested
    @DisplayName("Test the game evaluation methods")
    class TestGameEvaluation {
        @Test
        void testEvaluateNotWinButXHas2() {
            game.setBoard(BoardFixture.getTestBoard());
            assertEquals(5, game.evaluate("X"));
            assertEquals(0, game.evaluate("O"));
        }

        @Test
        void testEvaluateWinnerBoardByCol() {
            game.setBoard(BoardFixture.getXIsWinnerColumnBoard());
            assertEquals(10, game.evaluate("X"));
            assertEquals(-5, game.evaluate("O"));
        }

        @Test
        void testEvaluateWinnerBoardByRow() {
            game.setBoard(BoardFixture.buildBoard(new String[][]{
                    {"X", "X", " "},
                    {"O", "X", "X"},
                    {"O", "O", "O"}
            }));
            assertEquals(5, game.evaluate("X"));
            assertEquals(-10, game.evaluate("O"));
        }

        @Test
        void testEvaluateWinnerBoardByDiagonal() {
            game.setBoard(BoardFixture.buildBoard(new String[][]{
                    {"X", "X", "O"},
                    {"O", "X", " "},
                    {"O", " ", "X"}
            }));
            assertEquals(10, game.evaluate("X"));
            assertEquals(0, game.evaluate("O"));
        }
    }

    @Nested
    @DisplayName("Test the game state methods")
    class TestGetBoardState {
        @Test
        void testIsBoardFull() {
            game.setBoard(BoardFixture.getTestBoard());
            assertFalse(game.isBoardFull());
        }

        @Test
        void testHowManyCellsWithValue() {
            game.setBoard(BoardFixture.getTestBoard());
            assertEquals(3, game.getHowManyCellsWithValue("X"));
            assertEquals(3, game.getHowManyCellsWithValue("O"));
        }

        @Test
        void testWhichTurn() {
            game.setBoard(BoardFixture.getTestBoard());
            assertEquals("X", game.whichTurn());
        }

        @Test
        void testNoWinner() {
            game.setBoard(BoardFixture.getTestBoard());
            assertFalse(game.hasWinner("X"));
            assertFalse(game.hasWinner("O"));

        }

        @Test
        void testHasWinner() {
            game.setBoard(BoardFixture.getXIsWinnerColumnBoard());
            assertTrue(game.hasWinner("X"));
            assertFalse(game.hasWinner("O"));
        }

        @Test
        void testIsGameOverNo() {
            game.setBoard(BoardFixture.getTestBoard());
            assertFalse(game.isGameOver());
        }

        @Test
        void testIsGameOverYes() {
            game.setBoard(BoardFixture.getXIsWinnerColumnBoard());
            assertTrue(game.isGameOver());
        }

        @Test
        void testIsGameOverTie() {
            game.setBoard(BoardFixture.buildBoard(new String[][]{
                    {"O", "X", "O"},
                    {"O", "O", "X"},
                    {"X", "O", "X"}
            }));
            assertTrue(game.isGameOver());
        }

        @Test
        void testGetWinner() {
            game.setBoard(BoardFixture.getXIsWinnerColumnBoard());
            assertEquals("X", game.getWinner());
        }
    }





}