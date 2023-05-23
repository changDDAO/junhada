package com.changddao.junhada.repository.laptop;

import lombok.Data;

@Data
public class LaptopFileDto {
    Long id;
    String originName;

    public LaptopFileDto(Long id, String originName) {
        this.id = id;
        this.originName = originName;
    }
}
