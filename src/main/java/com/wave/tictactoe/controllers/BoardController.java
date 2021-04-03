package com.wave.tictactoe.controllers;

import com.wave.tictactoe.exceptions.InvalidBoardStateException;
import com.wave.tictactoe.services.BoardService;
import com.wave.tictactoe.validations.BoardStateValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * author: acerbk
 * Date: 4/2/21
 * Time: 3:41 AM
 */
@RestController
@RequestMapping(path = "/")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping(path = "/")
    ResponseEntity<?> getOptimalMove(@RequestParam("board") String currentBoardState) throws InvalidBoardStateException {

        currentBoardState = currentBoardState.replaceAll(" ", "+"); //we replace all space chars to + for processing

        //validate before processing,all validations happen here as a single source of truth
        BoardStateValidator.validateBoardState(currentBoardState);

        String result = boardService.getOptimalBoardState(currentBoardState);

        return ResponseEntity.ok().body(result);
    }
}
