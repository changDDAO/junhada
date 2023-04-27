package com.changddao.junhada.service;

import com.changddao.junhada.entity.LaptopFile;
import com.changddao.junhada.repository.laptop.LaptopFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LaptopFileService {
 @Value("${laptopFile.dir}")
 private String fileDir;

 private final LaptopFileRepository laptopFileRepository;

 public Long saveLaptopFile(MultipartFile files) throws IOException{
  if(files.isEmpty()) return null;
  //원래 파일 이름 추출
  String originName = files.getOriginalFilename();
  // 파일 이름으로 쓸 uuid 생성
  String uuid = UUID.randomUUID().toString();
  // 확장자 추출(ex : .png)
  String extension = originName.substring(originName.lastIndexOf("."));
  //uuid와 확장자 결합
  String savedName = uuid+extension;
  //파일을 불러올 때 사용할 파일경로
  String savedPath = fileDir+savedName;

  LaptopFile laptopFile = new LaptopFile(originName,savedName,savedPath);
  files.transferTo(new File(savedPath));
  LaptopFile savedLaptopFile = laptopFileRepository.save(laptopFile);
 return savedLaptopFile.getId();
 }

}
