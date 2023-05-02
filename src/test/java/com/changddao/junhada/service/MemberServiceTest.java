package com.changddao.junhada.service;

import com.changddao.junhada.entity.Address;
import com.changddao.junhada.entity.laptop.LaptopBoard;
import com.changddao.junhada.entity.laptop.LaptopReply;
import com.changddao.junhada.entity.Member;
import com.changddao.junhada.repository.MemberRepository;
import com.changddao.junhada.repository.laptop.LaptopBoardRepository;
import com.changddao.junhada.repository.laptop.LaptopReplyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
@SpringBootTest
@Transactional
class MemberServiceTest {
    @Autowired
    MemberService memberService;
    @Autowired
    LaptopBoardRepository laptopBoardRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    LaptopReplyRepository laptopReplyRepository;
    @Test
    public void 회원가입_테스트() throws Exception{
    //given
        Member member1 = new Member("younch8342@naver.com","창따오","a12345",
                new Address("261","대구","청수로","캐슬"));

        //when
        Long id = memberService.memberJoin(member1);

        //then
        assertThat(member1.getId()).isEqualTo(id);

    }

    @Test
    @Rollback(value = true)
    public void 회원가입_중복_테스트(){
    //given
        Member member1 = new Member("younch8342@naver.com","창따오","a12345",
                new Address("261","대구","청수로","캐슬"));
        Member member2 = new Member("kimchi@naver.com","기무찌","adsp12345",
                new Address("423","서울","용산구","캐슬"));
        Member member3 = new Member("pasta@naver.com","파스타","pasta123",
                new Address("400","부산","사하구","자이"));
        Member member4 = new Member("pasta@naver.com","파스타","pasta123",
                new Address("400","부산","사하구","자이"));

        memberService.memberJoin(member1);
        memberService.memberJoin(member2);
        memberService.memberJoin(member3);


    //when
        try{
            memberService.memberJoin(member4);
        } catch (IllegalStateException e){
            return;
        }

    }

    @Test
    public void 회원이_작성한_랩탑_글불러오기(){
    //given
        Member member1 = new Member("younch8342@naver.com","창따오","a12345",
                new Address("261","대구","청수로","캐슬"));
        Member member2 = new Member("kimchi@naver.com","기무찌","adsp12345",
                new Address("423","서울","용산구","캐슬"));
        Member member3 = new Member("pasta@naver.com","파스타","pasta123",
                new Address("400","부산","사하구","자이"));
        Long member1Id = memberService.memberJoin(member1);
        memberService.memberJoin(member2);
        memberService.memberJoin(member3);



        LaptopBoard laptopBoard1 = new LaptopBoard("삼성갤럭시북",3000000,"삼성갤럭시북 추천"
                ,"정말 좋습니다. 아주굿.");
        laptopBoard1.setMember(member1);
        laptopBoardRepository.save(laptopBoard1);
        LaptopBoard laptopBoard2
                = new LaptopBoard("삼성갤럭시북2",3000000,"사실 맥북사고싶음"
                ,"맥북이 사실 짱임ㅋㅋ");
        laptopBoard2.setMember(member1);
        laptopBoardRepository.save(laptopBoard2);

        LaptopBoard laptopBoard3
                = new LaptopBoard("삼성갤럭시북3",3000000,"사실 맥북사고싶음"
                ,"맥북이 사실 짱임ㅋㅋ");
        laptopBoard3.setMember(member2);
        laptopBoardRepository.save(laptopBoard3);
        LaptopBoard laptopBoard4
                = new LaptopBoard("삼성갤럭시북4",3000000,"사실 맥북사고싶음"
                ,"맥북이 사실 짱임ㅋㅋ");
        laptopBoard4.setMember(member2);
        laptopBoardRepository.save(laptopBoard4);
    //when
        List<LaptopBoard> laptopBoards = laptopBoardRepository.laptopBoardsByMember(member1Id);
        laptopBoards.stream().forEach(lb-> System.out.println("상품이름: "+lb.getProductName()));
        List<LaptopBoard> laptopBoards2 = laptopBoardRepository.laptopBoardsByMember(member2.getId());
        laptopBoards2.stream().forEach(lb-> System.out.println("상품이름2: "+lb.getProductName()));


        //then
    }
    @Test
    public void 멤버_조회_테스트(){
    //given
        Member member1 = new Member("younch8342@naver.com","창따오","a12345",
                new Address("261","대구","청수로","캐슬"));
        Member member2 = new Member("kimchi@naver.com","기무찌","adsp12345",
                new Address("423","서울","용산구","캐슬"));
        Member member3 = new Member("pasta@naver.com","파스타","pasta123",
                new Address("400","부산","사하구","자이"));
        Long member1Id = memberService.memberJoin(member1);
        memberService.memberJoin(member2);
        memberService.memberJoin(member3);
        //when
        //멤버 전체조회
        List<Member> members = memberService.findMembers();
        for (Member member : members) {
            System.out.println("member.getEmail() = " + member.getEmail());

        }
        //멤버 단건조회
        Member oneMember = memberService.findOneMember(member2.getId());
        System.out.println("oneMember.nickname = " + oneMember.getNickName());
        //then


    }
    
    @Test
    @Rollback(value = false)
    public void 멤버가_작성한_댓글_불러오기(){
    //given
        Member member1 = new Member("younch8342@naver.com","창따오","a12345",
                new Address("261","대구","청수로","캐슬"));
        Member member2 = new Member("kimchi@naver.com","기무찌","adsp12345",
                new Address("423","서울","용산구","캐슬"));
        Member member3 = new Member("pasta@naver.com","파스타","pasta123",
                new Address("400","부산","사하구","자이"));
        memberService.memberJoin(member1);
        memberService.memberJoin(member2);
        memberService.memberJoin(member3);

        LaptopBoard laptopBoard1 = new LaptopBoard("삼성갤럭시북",3000000,"삼성갤럭시북 추천"
                ,"정말 좋습니다. 아주굿.");
        laptopBoard1.setMember(member1);
        laptopBoardRepository.save(laptopBoard1);
        LaptopBoard laptopBoard2
                = new LaptopBoard("삼성갤럭시북2",3000000,"사실 맥북사고싶음"
                ,"맥북이 사실 짱임ㅋㅋ");
        laptopBoard2.setMember(member1);
        laptopBoardRepository.save(laptopBoard2);

        LaptopReply laptopReply1 = new LaptopReply("근데 가격이 좀 너무 비싼것같아요ㅠㅠ");
        laptopReply1.setMember(member2);
        laptopReply1.setLaptopBoard(laptopBoard1);
        laptopReplyRepository.save(laptopReply1);
        LaptopReply laptopReply3 = new LaptopReply("게이밍노트북인데 너무무거워서 불편해요ㅋㅋ");
        laptopReply3.setMember(member2);
        laptopReply3.setLaptopBoard(laptopBoard1);
        laptopReplyRepository.save(laptopReply3);


        LaptopReply laptopReply2 = new LaptopReply("에이 사실 개발자는 맥북이 짱이죠..");
        laptopReply2.setMember(member3);
        laptopReply2.setLaptopBoard(laptopBoard1);
        laptopReplyRepository.save(laptopReply2);

        //when
        List<LaptopReply> laptopRepliesByMember = laptopReplyRepository.findLaptopRepliesByMember(member2.getId());
        for (LaptopReply laptopReply : laptopRepliesByMember) {
            System.out.println("laptopReply.getReplyContent() = " + laptopReply.getReplyContent());
        }

        //then
    
    
    }


}