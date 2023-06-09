package com.changddao.junhada.repository.laptop;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LaptopReplyDto {
    private Long laptopReplyId;
    private String content;
    private String writer;
    private LocalDateTime writeDate;
    private LocalDateTime modifiedDate;

    @QueryProjection
    public LaptopReplyDto(Long laptopReplyId, String content, String writer, LocalDateTime writeDate, LocalDateTime modifiedDate) {
        this.laptopReplyId = laptopReplyId;
        this.content = content;
        this.writer = writer;
        this.writeDate = writeDate;
        this.modifiedDate = modifiedDate;
    }
}
