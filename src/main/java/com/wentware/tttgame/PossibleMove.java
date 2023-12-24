package com.wentware.tttgame;

import lombok.Getter;

import java.util.List;

//create graph of all possible moves of the game
@Getter
public class PossibleMove {
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
