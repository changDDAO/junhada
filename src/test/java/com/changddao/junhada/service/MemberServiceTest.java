package com.changddao.junhada.service;

import com.changddao.junhada.entity.Address;
import com.changddao.junhada.entity.Member;
import com.changddao.junhada.repository.MemberRepository;
import com.changddao.junhada.repository.laptop.LaptopBoardRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class MemberServiceTest {
    @Autowired
    MemberService memberService;
    @Autowired
    LaptopBoardRepository laptopBoardRepository;
    @Autowired
    MemberRepository memberRepository;
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


}