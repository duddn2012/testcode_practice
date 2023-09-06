package com.lets.git.it.letsgitit.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.lets.git.it.letsgitit.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public class BoardRes {
    private Long boardId;
    private String title;
    private String content;
    private Long userId;

    public static BoardRes fromEntity(Board board) {
        return new BoardRes(
                board.getId(),
                board.getTitle(),
                board.getContent(),
                board.getUserId()
        );
    }
}