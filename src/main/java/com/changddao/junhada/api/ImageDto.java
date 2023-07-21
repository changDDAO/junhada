package com.changddao.junhada.api;

import com.changddao.junhada.entity.laptop.LaptopFile;
import lombok.Getter;

@Getter
public class ImageDto {
    Long id;
    String savedPath;
    String imageLink;

    public ImageDto(LaptopFile laptopFile) {
        this.id = laptopFile.getId();
        this.savedPath = laptopFile.getSavedPath();
        this.imageLink = "ddaowifi.iptime.org:8080/api/images/"+String.valueOf(id);
    }
}
