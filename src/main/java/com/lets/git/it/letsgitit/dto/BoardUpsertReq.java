package com.lets.git.it.letsgitit.dto;

import com.lets.git.it.letsgitit.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BoardUpsertReq {
    private String title;
    private String content;
    private Long userId;

    public static Board toEntity(BoardUpsertReq dto) {
        return new Board(
                dto.getTitle(),
                dto.getContent(),
                dto.getUserId()
        );
    }
}