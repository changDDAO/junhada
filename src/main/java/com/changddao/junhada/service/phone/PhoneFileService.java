package com.changddao.junhada.service.phone;
import com.changddao.junhada.entity.phone.PhoneBoard;
import com.changddao.junhada.entity.phone.PhoneFile;
import com.changddao.junhada.repository.phone.PhoneFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PhoneFileService {
    @Value("${phoneFile.dir}")
    private String fileDir;
    private final PhoneFileRepository phoneFileRepository;
    public Long savePhoneFile(MultipartFile files, PhoneBoard phoneBoard) throws IOException {
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

        PhoneFile phoneFile = new PhoneFile(originName,savedName,savedPath);
        phoneFile.setPhoneBoard(phoneBoard); // 보드를 지정해주는 로직 들어가야함
        files.transferTo(new File(savedPath));
        PhoneFile savedPhoneFile = phoneFileRepository.save(phoneFile);
        return savedPhoneFile.getId();
    }
}
