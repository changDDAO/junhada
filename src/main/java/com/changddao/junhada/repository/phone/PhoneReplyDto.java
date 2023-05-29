package com.changddao.junhada.repository.phone;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PhoneReplyDto {
    private String content;
    private String writer;
    private LocalDateTime writeDate;
    private LocalDateTime modifiedDate;
    @QueryProjection
    public PhoneReplyDto(String content, String writer, LocalDateTime writeDate, LocalDateTime modifiedDate) {
        this.content = content;
        this.writer = writer;
        this.writeDate = writeDate;
        this.modifiedDate = modifiedDate;
    }
}
