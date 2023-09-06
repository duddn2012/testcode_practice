package com.lets.git.it.letsgitit.controller;

import com.google.gson.Gson;
import com.lets.git.it.letsgitit.dto.BoardRes;
import com.lets.git.it.letsgitit.dto.BoardUpsertReq;
import com.lets.git.it.letsgitit.entity.Board;
import com.lets.git.it.letsgitit.repository.BoardRepository;
import com.lets.git.it.letsgitit.service.BoardService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BoardController.class)
class BoardControllerTest {
    @Autowired MockMvc mockMvc;
    @MockBean private BoardService boardService;

    @Test
    @DisplayName("게시판 조회")
    void getBoard() throws Exception {
        //given
        given(boardService.findAll()).willReturn(
                List.of(new BoardRes(1L,"hello world","abc",1L)
                ,new BoardRes(2L,"hello world2","abc2",2L)));


        //when - then
        mockMvc.perform(
                get("/boards"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].boardId").exists())
                .andExpect(jsonPath("$[0].title").exists())
                .andExpect(jsonPath("$[0].content").exists())
                .andExpect(jsonPath("$[0].userId").exists())
                .andExpect(jsonPath("$[1].boardId").exists())
                .andExpect(jsonPath("$[1].title").exists())
                .andExpect(jsonPath("$[1].content").exists())
                .andExpect(jsonPath("$[1].userId").exists())
                .andDo(print());

        verify(boardService).findAll();
    }

    @Test
    @DisplayName("게시판 생성")
    void create() throws Exception {
        given(boardService.saveBoard(new BoardUpsertReq("hello world","abc",1L))).willReturn(
                new BoardRes(1L,"hello world","abc",1L)
        );

        BoardUpsertReq boardUpsertReq = new BoardUpsertReq("hello world","abc",1L);

        Gson gson = new Gson();
        String content = gson.toJson(boardUpsertReq);

        mockMvc.perform(
                post("/boards")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                //TODO 이 경우 값이 Response가 세팅이 안되는 이유??
                //.andExpect(jsonPath("$.boardId").exists())
                //.andExpect(jsonPath("$.title").exists())
                //.andExpect(jsonPath("$.content").exists())
                //.andExpect(jsonPath("$.userId").exists())
                .andDo(print());
    }

    @Test
    @DisplayName("게시판 삭제")
    void deleteBoard() throws Exception {
        //TODO 이런 데이터를 넣는 것이 의미가 있는지? 주석처리해도 정상 처리됨
        given(boardService.saveBoard(new BoardUpsertReq("hello world","abc",1L))).willReturn(
                new BoardRes(1L,"hello world","abc",1L)
        );

        Long boardId = 1L;

        mockMvc.perform(
                        delete("/boards/"+boardId))
                .andExpect(status().isOk())
                .andDo(print());
    }
}