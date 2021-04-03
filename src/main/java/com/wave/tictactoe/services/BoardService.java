package com.wave.tictactoe.services;

import com.wave.tictactoe.base.OptimalMoveGenerator;
import org.springframework.stereotype.Service;

/**
 * author: acerbk
 * Date: 4/2/21
 * Time: 3:47 AM
 */
@Service
public class BoardService {

    private final OptimalMoveGenerator optimalMoveGenerator;

    public BoardService(OptimalMoveGenerator optimalMoveGenerator) {
        this.optimalMoveGenerator = optimalMoveGenerator;
    }

    public String getOptimalBoardState(String currentBoardState) {
        return optimalMoveGenerator.generateOptimalMove(currentBoardState);
    }
}
