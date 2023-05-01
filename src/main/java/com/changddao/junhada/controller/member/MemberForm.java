package com.changddao.junhada.controller.member;

import com.changddao.junhada.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.UniqueConstraint;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberForm {
    @Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "이메일 형식이 올바르지 않습니다.")
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    private String email;
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$", message = "닉네임은 특수문자를 제외한 2~10자리여야 합니다.")
    private String nickName;
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    private String password;
    @NotBlank(message = "우편번호 입력은 필수입니다.")
    private String zipcode;
    @NotBlank(message = "도시 입력은 필수입니다.")
    private String city;
    @NotBlank(message = "거리 입력은 필수입니다.")
    private String street;
    @NotBlank(message = "상세주소 입력은 필수입니다.")
    private String detail;
}
