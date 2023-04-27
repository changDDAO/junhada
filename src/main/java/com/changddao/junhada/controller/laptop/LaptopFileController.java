package com.changddao.junhada.controller.laptop;

import com.changddao.junhada.service.LaptopFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/laptop")
public class LaptopFileController {
    private final LaptopFileService laptopFileService;

    @GetMapping("/upload")
    public String uploadForm(){
        return "files/laptopFileUpload";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("laptopFile")MultipartFile laptopFile,
                             @RequestParam("laptopFiles")List<MultipartFile> laptopFiles)
        throws IOException{
        laptopFileService.saveLaptopFile(laptopFile);
        for(MultipartFile multipartFile :laptopFiles){
            laptopFileService.saveLaptopFile(multipartFile);
        }
        return "redirect:/";
    }


}
