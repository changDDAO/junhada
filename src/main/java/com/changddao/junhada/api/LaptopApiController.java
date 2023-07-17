package com.changddao.junhada.api;

import com.changddao.junhada.entity.laptop.LaptopFile;
import com.changddao.junhada.repository.laptop.LaptopFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
