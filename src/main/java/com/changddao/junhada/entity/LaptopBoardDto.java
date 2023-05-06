package com.changddao.junhada.entity;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;

@Data

public class LaptopBoardDto {
    private Long boardId;
    private String title;
    private String nickName;
    @QueryProjection
    public LaptopBoardDto(Long boardId, String title, String nickName) {
        this.boardId = boardId;
        this.title = title;
        this.nickName = nickName;
    }
}
