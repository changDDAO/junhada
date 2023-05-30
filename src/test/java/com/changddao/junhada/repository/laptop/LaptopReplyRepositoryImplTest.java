package com.changddao.junhada.repository.laptop;

import com.changddao.junhada.entity.Address;
import com.changddao.junhada.entity.Member;
import com.changddao.junhada.entity.laptop.LaptopBoard;
import com.changddao.junhada.entity.laptop.LaptopReply;
import com.changddao.junhada.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class LaptopReplyRepositoryImplTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    LaptopReplyRepository laptopReplyRepository;
    @Autowired
    LaptopBoardRepository laptopBoardRepository;
    @Test
    public void 댓글_Dto_테스트(){
    //given
        Member member1 = new Member("younch8342@naver.com","창따오","a12345",
                new Address("261","대구","청수로","캐슬"));
        memberRepository.save(member1);
        Member member2 = new Member("kimchi@naver.com","기무찌","adsp12345",
                new Address("423","서울","용산구","캐슬"));
        memberRepository.save(member2);


        //when
        LaptopBoard laptopBoard = new LaptopBoard("삼성갤럭시북",3000000,"삼성갤럭시북 추천"
                ,"정말 좋습니다. 정말 좋습니다. 정말 좋습니다.정말 좋습니다.정말 좋습니다. 정말 좋습니다.정말 좋습니다.정말 좋습니다." +
                "정말 좋습니다. 정말 좋습니다. 정말 좋습니다. 정말 좋습니다.정말 좋습니다.정말 좋습니다.정말 좋습니다.정말 좋습니다.");
        laptopBoard.setMember(member1);
        laptopBoardRepository.save(laptopBoard);

        LaptopBoard laptopBoard2 = new LaptopBoard("삼성갤럭시북2",2000000,"삼성갤럭시북2 추천"
                ,"정말 별로입니다.");
        laptopBoard2.setMember(member2);

        LaptopReply laptopReply = new LaptopReply("좋네요");
        laptopReply.setLaptopBoard(laptopBoard);
        laptopReply.setMember(member1);
        laptopReplyRepository.save(laptopReply);

        LaptopReply laptopReply2 = new LaptopReply("기가맥히");
        laptopReply2.setLaptopBoard(laptopBoard);
        laptopReply2.setMember(member2);
        laptopReplyRepository.save(laptopReply2);
        LaptopReply laptopReply3 = new LaptopReply("헐랭구");
        laptopReply3.setLaptopBoard(laptopBoard);
        laptopReply3.setMember(member2);
        laptopReplyRepository.save(laptopReply3);

        //when
        //댓글 삭제


        }


        //then


    }

