package com.changddao.junhada.repository.laptop;

import com.changddao.junhada.entity.laptop.LaptopBoard;
import com.changddao.junhada.entity.laptop.LaptopFile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class LaptopFileRepositoryTest {
    @Autowired
    LaptopBoardRepository laptopBoardRepository;
    @Autowired
    LaptopFileRepository laptopFileRepository;
    @Test
    public void 저장경로_테스트(){
    //given
        LaptopBoard laptopBoard = new LaptopBoard("삼성갤럭시북",3000000,"삼성갤럭시북 추천"
                ,"정말 좋습니다. 정말 좋습니다. 정말 좋습니다.정말 좋습니다.정말 좋습니다. 정말 좋습니다.정말 좋습니다.정말 좋습니다." +
                "정말 좋습니다. 정말 좋습니다. 정말 좋습니다. 정말 좋습니다.정말 좋습니다.정말 좋습니다.정말 좋습니다.정말 좋습니다.");
        laptopBoardRepository.save(laptopBoard);

        LaptopBoard laptopBoard2 = new LaptopBoard("삼성갤럭시북2",2000000,"삼성갤럭시북2 추천"
                ,"정말 별로입니다.");
        laptopBoardRepository.save(laptopBoard2);
        LaptopFile laptopFile = new LaptopFile("a", "b", "/src/c");
        LaptopFile laptopFile1 = new LaptopFile("b", "C", "/src/d");
        LaptopFile laptopFile2 = new LaptopFile("s", "h", "/src/e");
        laptopFile.setLaptopBoard(laptopBoard);
        laptopFile1.setLaptopBoard(laptopBoard);
        laptopFile2.setLaptopBoard(laptopBoard);
        laptopFileRepository.save(laptopFile);
        laptopFileRepository.save(laptopFile1);
        laptopFileRepository.save(laptopFile2);



        //when



    //then


    }

}