package com.wave.tictactoe.validations;

import com.wave.tictactoe.converters.BoardStateConverter;
import com.wave.tictactoe.exceptions.InvalidBoardStateException;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

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
     * @param boardStateString
     * @throws InvalidBoardStateException
     */
    public static void validateBoardState(String boardStateString) throws InvalidBoardStateException {

        if (boardStateString.length() != 9)//string must not be less than or greater than 9
            throw new InvalidBoardStateException();

        String allPossibleCharsStripped = boardStateString
                .replaceAll("o", "")
                .replaceAll("x", "")
                .replaceAll(Pattern.quote("+"), "");
        if (!allPossibleCharsStripped.isEmpty()) //after stripping all possible characters,our string should be empty,otherwise, then we throw invalidboardstateexception
            throw new InvalidBoardStateException();

        char[][] boardMatrixArray = BoardStateConverter.convertToMatrixArray(boardStateString);

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
