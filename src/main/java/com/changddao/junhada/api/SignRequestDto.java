package com.changddao.junhada.api;

import com.changddao.junhada.entity.Address;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignRequestDto {
    private String email;
    private String nickName;
    private String password;
    private String zipcode;
    private String city;
    private String street;
    private String detail;
}
