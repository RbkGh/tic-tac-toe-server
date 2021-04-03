package com.wave.tictactoe.base;

import com.wave.tictactoe.exceptions.InvalidBoardStateException;

/**
 * author: acerbk
 * Date: 4/2/21
 * Time: 4:05 AM
 * <p>
 * This interface generates the best next move possible by the AI PLAYER_SYMBOL( AI PLAYER_SYMBOL in this context is the computer)
 * </p>
 */
public interface OptimalMoveGenerator {

    /**
     * generate the next best move by the AI PLAYER_SYMBOL and append it to the current board state and return as string
     *
     * @param currentBoardState
     * @return
     * @throws InvalidBoardStateException
     */
    String generateOptimalMove(String currentBoardState) throws InvalidBoardStateException;
}
