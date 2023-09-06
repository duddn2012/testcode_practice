package com.lets.git.it.letsgitit.service;

import com.lets.git.it.letsgitit.dto.BoardRes;
import com.lets.git.it.letsgitit.dto.BoardUpsertReq;
import com.lets.git.it.letsgitit.entity.Board;
import com.lets.git.it.letsgitit.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    //object -> 객체지향


    private final BoardRepository boardRepository;
    public List<BoardRes> findAll() {
        List<Board> boardList = boardRepository.findAll();
        return boardList.stream().map(it->BoardRes.fromEntity(it)).collect(Collectors.toList());
    }

    public BoardRes saveBoard(BoardUpsertReq dto) {
        Board board = boardRepositorSy.save(BoardUpsertReq.toEntity(dto));
        return BoardRes.fromEntity(board);
    }

    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }
}
