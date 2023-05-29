package com.changddao.junhada.repository.phone;

import lombok.Data;

@Data
public class PhoneFileDto {
    Long id;
    String originName;

    public PhoneFileDto(Long id, String originName) {
        this.id = id;
        this.originName = originName;
    }
}
