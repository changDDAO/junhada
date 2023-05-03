package com.changddao.junhada.controller.member;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
public class LoginForm {
    @Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "아이디 형식이 올바르지 않습니다.")
    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    private String email;
    @NotBlank(message = "비밀번호를 입력하세요")
    private String password;
}
