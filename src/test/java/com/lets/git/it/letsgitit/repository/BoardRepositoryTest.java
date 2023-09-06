package com.lets.git.it.letsgitit.repository;

import com.lets.git.it.letsgitit.entity.Board;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BoardRepositoryTest {

    @Autowired private BoardRepository boardRepository;
    @Autowired private EntityManager em;


    @Test
    @DisplayName("게시물 전체 조회 테스트")
    void findAll() {
        //given
        Board board1 = new Board("title","content",1L);
        boardRepository.save(board1);
        Board board2 = new Board("title2","content2",1L);
        boardRepository.save(board2);

        //when
        List<Board> boardList = boardRepository.findAll();

        //then
        assertThat(boardList.get(0).getId()).isEqualTo(1L);
        assertThat(boardList.get(0).getTitle()).isEqualTo("title");
        assertThat(boardList.get(0).getContent()).isEqualTo("content");
        assertThat(boardList.get(0).getUserId()).isEqualTo(1L);

        assertThat(boardList.get(1).getId()).isEqualTo(2L);
        assertThat(boardList.get(1).getTitle()).isEqualTo("title2");
        assertThat(boardList.get(1).getContent()).isEqualTo("content2");
        assertThat(boardList.get(1).getUserId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("게시글 저장")
    void save() {
        //given
        Board board = Board.builder().title("title").content("content").userId(1L).build();

        //when
        boardRepository.save(board);
        Board boardResult = boardRepository.findById(board.getId()).get();

        //then
        assertThat(boardResult.getId()).isEqualTo(board.getId());
        assertThat(boardResult.getTitle()).isEqualTo(board.getTitle());
        assertThat(boardResult.getContent()).isEqualTo(board.getContent());
        assertThat(boardResult.getUserId()).isEqualTo(board.getUserId());
    }

    @Test
    @DisplayName("게시글 삭제")
    void deleteById() {
        //given
        Board board = Board.builder().title("title").content("content").userId(1L).build();
        boardRepository.save(board);

        //when
        boardRepository.delete(board);
        em.flush();
        em.clear();

        Optional<Board> boardResult = boardRepository.findById(1L);

        //then
        assertThat(boardResult).isEmpty();
    }
}