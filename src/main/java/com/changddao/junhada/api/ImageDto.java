package com.changddao.junhada.api;

import com.changddao.junhada.entity.laptop.LaptopFile;
import lombok.Getter;

@Getter
public class ImageDto {
    Long id;
    String savedPath;

    public ImageDto(LaptopFile laptopFile) {
        this.id = laptopFile.getId();
        this.savedPath = laptopFile.getSavedPath();
    }
}
