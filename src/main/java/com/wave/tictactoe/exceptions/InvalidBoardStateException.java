package com.wave.tictactoe.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * author: acerbk
 * Date: 4/2/21
 * Time: 4:09 AM
 * <p>
 * This exception throws a 400 Bad request to denote that the board state to be processed is invalid.
 */
@Data
@ResponseStatus(HttpStatus.BAD_REQUEST)
@EqualsAndHashCode(callSuper = false)
public class InvalidBoardStateException extends RuntimeException {
}
