package com.wave.tictactoe.models;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * author: acerbk
 * Date: 4/2/21
 * Time: 5:01 AM
 * Best Move location on tic-tac-toe board, row and column specifies the location
 */
@Data
@NoArgsConstructor
public class SingleMove {
    private int row;
    private int column;

    public SingleMove(int row, int column) {
        this.row = row;
        this.column = column;
    }
}
