package com.changddao.junhada.api;

import com.changddao.junhada.entity.laptop.LaptopFile;
import com.changddao.junhada.repository.laptop.LaptopFileRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class LaptopApiController {
    private final LaptopFileRepository laptopFileRepository;
    @GetMapping("/api/images")
    public List<ImageDto> imageDtoList(){
        List<LaptopFile> all = laptopFileRepository.findAll();
        return all.stream().map(laptopFile -> new ImageDto(laptopFile)).collect(Collectors.toList());
    }

    //view에 업로드한 이미지 출력하는 부분
    @GetMapping(value = "/api/images/{laptopFileId}")
    public ResponseEntity<Resource> printImage(@PathVariable("laptopFileId") Long id) throws IOException, URISyntaxException {
        LaptopFile laptopFile = laptopFileRepository.findById(id).orElse(null);
        if (laptopFile==null) {
            System.out.println(Font.SERIF+"File : Not_Found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Resource resource = new UrlResource("file:"+laptopFile.getSavedPath());
        HttpHeaders header = new HttpHeaders();
        Path filePath = null;
        try {
            //filePath = Paths.get(resource.getURI());
            header.add("Content-Type", Files.probeContentType(resource.getFile().toPath()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<Resource>(resource,header, HttpStatus.OK);



    }

}
