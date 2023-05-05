package com.changddao.junhada.controller.member;

import com.changddao.junhada.entity.Authority;
import com.changddao.junhada.entity.Member;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class MemberSignResponse {
    private Long memberId;
    private String email;
    private String nickName;
    private List<Authority> roles = new ArrayList<>();
    private String token;


}
