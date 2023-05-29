package com.changddao.junhada.repository.phone;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class PhoneBoardDto {
    private Long boardId;
    private String title;
    private String nickName;
    @QueryProjection
    public PhoneBoardDto(Long boardId, String title, String nickName) {
        this.boardId = boardId;
        this.title = title;
        this.nickName = nickName;
    }
}
