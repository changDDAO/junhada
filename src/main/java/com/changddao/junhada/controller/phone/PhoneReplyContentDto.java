package com.changddao.junhada.controller.phone;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class PhoneReplyContentDto {
    @NotBlank(message="댓글을 입력하세요")
    @Length(max=200, message="200자 이내로 입력하세요")
    private String Content;
}
