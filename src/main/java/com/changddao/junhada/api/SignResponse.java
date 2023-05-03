package com.changddao.junhada.api;

import com.changddao.junhada.entity.Address;
import com.changddao.junhada.entity.Authority;
import com.changddao.junhada.entity.Member;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignResponse {
    private Long id;
    private String email;
    private String nickName;
    private String password;

    private Address address;
    private List<Authority> roles = new ArrayList<>();
    private String token;

    public SignResponse(Member member) {
        this.id = member.getId();
        this.email= member.getEmail();
        this.nickName =member.getNickName();
        this.password = member.getPassword();
        this. address = member.getAddress();
        this.roles = member.getRoles();
    }
}
