package com.wave.tictactoe.validations;

import com.wave.tictactoe.exceptions.InvalidBoardStateException;

import java.util.HashMap;
import java.util.Map;

/**
 * author: acerbk
 * Date: 4/2/21
 * Time: 5:15 AM
 * Validate the state of a board, throw {@linkplain InvalidBoardStateException} if it is invalid
 */
public class BoardStateValidator {

    /**
     * validate board state , whether optimal move can be generated or not, if board is not in a correct state,
     * {@link InvalidBoardStateException} is thrown which returns 400 status code in turn
     *
     * @param boardMatrixArray
     * @throws InvalidBoardStateException
     */
    public static void validateBoardState(char[][] boardMatrixArray) throws InvalidBoardStateException {

        Map<Character, Integer> characterFrequencyMap = new HashMap<>();
        //characters are either o,x or + only so we initially all to a count of zero
        characterFrequencyMap.put('o', 0); //character values must be externalized into a properties file so that it can be changed easily but for brevity sake ,it's hardcoded
        characterFrequencyMap.put('x', 0);
        characterFrequencyMap.put('+', 0);

        //first row
        for (int i = 0; i < 3; i++) {
            char currentChar = boardMatrixArray[0][i];

            int currentCharCountPlusOne = characterFrequencyMap.getOrDefault(currentChar, 0) + 1;
            //increase the count of the character by 1 as it has already been initialized to 0 abave
            characterFrequencyMap.put(currentChar, currentCharCountPlusOne);
        }

        //second row
        for (int i = 0; i < 3; i++) {
            char currentChar = boardMatrixArray[1][i];

            int currentCharCountPlusOne = characterFrequencyMap.getOrDefault(currentChar, 0) + 1;
            //increase the count of the character by 1 as it has already been initialized to 0 abave
            characterFrequencyMap.put(currentChar, currentCharCountPlusOne);
        }

        //third row
        for (int i = 0; i < 3; i++) {
            char currentChar = boardMatrixArray[2][i];

            int currentCharCountPlusOne = characterFrequencyMap.getOrDefault(currentChar, 0) + 1;
            //increase the count of the character by 1 as it has already been initialized to 0 abave
            characterFrequencyMap.put(currentChar, currentCharCountPlusOne);
        }


        if (characterFrequencyMap.getOrDefault('o', 0) > characterFrequencyMap.getOrDefault('x', 0)) {
            //throw Invalid Board state exception since it can't be o's turn as it's more than total occurences of x
            throw new InvalidBoardStateException();
        }
    }
}
