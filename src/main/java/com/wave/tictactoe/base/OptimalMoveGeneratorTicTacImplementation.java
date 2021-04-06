package com.wave.tictactoe.base;

import com.wave.tictactoe.converters.BoardStateConverter;
import com.wave.tictactoe.exceptions.InvalidBoardStateException;
import com.wave.tictactoe.models.SingleMove;

/**
 * author: acerbk
 * Date: 4/2/21
 * Time: 4:20 AM
 * <p>
 * Tic-tac-toe implementation of {@link OptimalMoveGenerator}, <br>
 * this means that we can make different implementations <br>
 * eg, Chess and others and just configure <br>
 * which ones should be used in our {@link com.wave.tictactoe.configs.AppMainConfigurator}
 */
public class OptimalMoveGeneratorTicTacImplementation implements OptimalMoveGenerator {

    private static char PLAYER_SYMBOL = 'o', OPPONENT_SYMBOL = 'x';

    @Override
    public String generateOptimalMove(String currentBoardState) throws InvalidBoardStateException {

        char[][] boardStateArray = BoardStateConverter.convertToMatrixArray(currentBoardState);

        SingleMove optimalSingleMove = findOptimalSingleMove(boardStateArray);

        return BoardStateConverter.convertToString(boardStateArray, optimalSingleMove);
    }

    /**
     * find most optimal move for PLAYER_SYMBOL
     *
     * @param boardStateArray
     * @return
     */
    private SingleMove findOptimalSingleMove(char[][] boardStateArray) {

        int bestVal = -1000;
        SingleMove singleBestMove = new SingleMove(-1, -1);

        // Traverse all cell values, perform a minimax evaluation for all empty cells and return optimal value
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                //check whether cell is empty
                if (boardStateArray[i][j] == '+') {
                    // Make the move
                    boardStateArray[i][j] = PLAYER_SYMBOL;

                    // compute evaluation function for this
                    // move.
                    int moveVal = minimax(boardStateArray, 0, true);

                    // Undo the move
                    boardStateArray[i][j] = '+';

                    // If the value of the current move is
                    // more than the best value, then update
                    // best
                    if (moveVal > bestVal) {
                        singleBestMove.setRow(i);
                        singleBestMove.setColumn(j);
                        bestVal = moveVal;
                    }
                }
            }
        }

        return singleBestMove;
    }

    /**
     * Takes into account all possible variations the game can go and returns state value of board
     *
     * @param boardMatrixArray
     * @param depth
     * @param isMax
     * @return
     */
    private int minimax(char boardMatrixArray[][],
                        int depth, boolean isMax) {
        int score = evaluate(boardMatrixArray);

        // If Maximizer has won the game
        // return his/her evaluated score
        if (score == 10)
            return score - depth;

        // If Minimizer has won the game
        // return his/her evaluated score
        if (score == -10)
            return score - depth;

        // If there are no more moves and
        // no winner then it is a tie
        if (!areMovesRemaining(boardMatrixArray))
            return 0 - depth;

        // If this maximizer's move
        if (isMax) {
            int best = -1000;

            // Traverse all cells
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Check if cell is empty
                    if (boardMatrixArray[i][j] == '+') {
                        // Make the move
                        boardMatrixArray[i][j] = PLAYER_SYMBOL;

                        // Call minimax recursively and choose
                        // the maximum value
                        best = Math.max(best, minimax(boardMatrixArray,
                                depth + 1, !isMax));

                        // Undo the move
                        boardMatrixArray[i][j] = '+';
                    }
                }
            }
            return best;
        }

        // If this minimizer's move
        else {
            int best = 1000;

            // Traverse all cells
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Check if cell is empty
                    if (boardMatrixArray[i][j] == '+') {
                        // Make the move
                        boardMatrixArray[i][j] = OPPONENT_SYMBOL;

                        // Call minimax recursively and choose
                        // the minimum value
                        best = Math.min(best, minimax(boardMatrixArray,
                                depth + 1, !isMax));

                        // Undo the move
                        boardMatrixArray[i][j] = '+';
                    }
                }
            }
            return best;
        }
    }


    /**
     * evaluate the diagonals, rows and columns to possibly find a victor, otherwise return 0 gracefully
     *
     * @param boardArray
     * @return
     */
    private int evaluate(char boardArray[][]) {
        //check if PLAYER_SYMBOL or OPPONENT_SYMBOL is the victor in the rows
        for (int row = 0; row < 3; row++) {
            if (boardArray[row][0] == boardArray[row][1] &&
                    boardArray[row][1] == boardArray[row][2]) {
                if (boardArray[row][0] == PLAYER_SYMBOL)
                    return +10;
                else if (boardArray[row][0] == OPPONENT_SYMBOL)
                    return -10;
            }
        }

        //check if PLAYER_SYMBOL or OPPONENT_SYMBOL is the victor in the columns
        for (int col = 0; col < 3; col++) {
            if (boardArray[0][col] == boardArray[1][col] &&
                    boardArray[1][col] == boardArray[2][col]) {
                if (boardArray[0][col] == PLAYER_SYMBOL)
                    return +10;

                else if (boardArray[0][col] == OPPONENT_SYMBOL)
                    return -10;
            }
        }

        //check if PLAYER_SYMBOL or OPPONENT_SYMBOL is the victor in the diagonals
        if (boardArray[0][0] == boardArray[1][1] && boardArray[1][1] == boardArray[2][2]) {
            if (boardArray[0][0] == PLAYER_SYMBOL)
                return +10;
            else if (boardArray[0][0] == OPPONENT_SYMBOL)
                return -10;
        }

        if (boardArray[0][2] == boardArray[1][1] && boardArray[1][1] == boardArray[2][0]) {
            if (boardArray[0][2] == PLAYER_SYMBOL)
                return +10;
            else if (boardArray[0][2] == OPPONENT_SYMBOL)
                return -10;
        }
        //if neither OPPONENT_SYMBOL nor PLAYER_SYMBOL won, then return 0
        return 0;
    }


    /**
     * Returns true if there are more moves remaining on the board
     *
     * @param board
     * @return
     */
    private boolean areMovesRemaining(char board[][]) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == '+')
                    return true;
        return false;
    }
}
