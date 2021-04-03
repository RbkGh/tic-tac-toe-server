package com.wave.tictactoe.converters;

import com.wave.tictactoe.exceptions.InvalidBoardStateException;
import com.wave.tictactoe.models.SingleMove;

import java.util.regex.Pattern;

/**
 * author: acerbk
 * Date: 4/2/21
 * Time: 5:15 AM
 * Convert board state to and from string to char array
 */
public class BoardStateConverter {

    /**
     * converts string representation of board state into a 3x3 array for processing
     *
     * @param boardState
     * @return
     * @throws InvalidBoardStateException
     */
    public static char[][] convertToMatrixArray(String boardState) throws InvalidBoardStateException {
        if (boardState.length() != 9)//string must not be less than or greater than 9
            throw new InvalidBoardStateException();

        char[] initialCharArray = boardState.toCharArray();

        char[][] charFinal = {
                //first row
                {initialCharArray[0], initialCharArray[1], initialCharArray[2]},
                //second row
                {initialCharArray[3], initialCharArray[4], initialCharArray[5]},
                //third row
                {initialCharArray[6], initialCharArray[7], initialCharArray[8]}
        };

        return charFinal;
    }

    /**
     * convert matrix array to string to be returned as response
     *
     * @param boardStateArray
     * @param optimisedMove
     * @return
     */
    public static String convertToString(char[][] boardStateArray, SingleMove optimisedMove) {

        //get the location to append the optimised value and append o
        boardStateArray[optimisedMove.getRow()][optimisedMove.getColumn()] = 'o';

        //add characters iteratively to final string to be returned,
        String finalString = String.valueOf(
                boardStateArray[0][0]) +
                boardStateArray[0][1] +
                boardStateArray[0][2] +
                boardStateArray[1][0] +
                boardStateArray[1][1] +
                boardStateArray[1][2] +
                boardStateArray[2][0] +
                boardStateArray[2][1] +
                boardStateArray[2][2];

        //strip all + symbols and replace with empty space in final string
        String finalStringStripped = finalString.replaceAll(Pattern.quote("+"), " ");

        return finalStringStripped;
    }
}
