package com.lets.git.it.letsgitit.service;

import com.lets.git.it.letsgitit.dto.BoardRes;
import com.lets.git.it.letsgitit.dto.BoardUpsertReq;
import com.lets.git.it.letsgitit.entity.Board;
import com.lets.git.it.letsgitit.repository.BoardRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@Import({BoardService.class, BoardRepository.class})
class BoardServiceTest {
    @MockBean
    BoardRepository boardRepository;

    @Autowired
    BoardService boardService;

    @Test
    void findAll() {
        //given mockito
        when(boardRepository.findAll())
                .thenReturn(List.of(new Board("title","content",1L), new Board("title2","content2",1L)));

        //when
        List<BoardRes> boardResList = boardService.findAll();

        //then
        assertThat(boardResList.get(0).getTitle()).isEqualTo("title");
        assertThat(boardResList.get(1).getTitle()).isEqualTo("title2");

        verify(boardRepository).findAll();
    }

    @Test
    void saveBoard() {
        //BDD
        given(boardRepository.save(any(Board.class))).willReturn(
                new Board("title","content",1L)
        );

        //TODO id를 auto increment하기 때문에 생성자를 통한 생성으로는 테스트가 불가능한듯 해결 방법이 뭘까?

        //when
        when(mock(Board.class).getId()).thenReturn(1L);
        BoardRes res = boardService.saveBoard(new BoardUpsertReq("title","content",1L));

        //then
        assertThat(res.getTitle()).isEqualTo("title");
        assertThat(res.getContent()).isEqualTo("content");

        verify(boardRepository).save(any(Board.class));
    }

    @Test
    void deleteBoard() {
        //BDD
        given(boardRepository.save(new Board("title","content",1L))).willReturn(
                new Board("title","content",1L)
        );

        //when

        //then
        assertThatNoException().isThrownBy(()->boardService.deleteBoard(1L));

        //verify(boardRepository).save(new Board("title","content",1L));
    }
}