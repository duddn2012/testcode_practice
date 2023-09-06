package com.lets.git.it.letsgitit.controller;

import com.lets.git.it.letsgitit.dto.BoardRes;
import com.lets.git.it.letsgitit.dto.BoardUpsertReq;
import com.lets.git.it.letsgitit.entity.Board;
import com.lets.git.it.letsgitit.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping
    public ResponseEntity<List<BoardRes>> getBoard() {
        List<BoardRes> res = boardService.findAll();
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BoardRes> create(@RequestBody BoardUpsertReq dto) {
        BoardRes res = boardService.saveBoard(dto);
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
