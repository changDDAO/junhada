package com.changddao.junhada.api;

import com.changddao.junhada.entity.Address;
import com.changddao.junhada.entity.Member;
import com.changddao.junhada.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class SignServiceTest {
    @Autowired
    MemberRepository memberRepository;
    @Test
    public void register(){

    //given
    Member member = Member.builder()
            .email("changho@naver.com")
            .address(new Address("422","daegu","chungsuro","castle"))
            .nickName("changddao")
            .password("password")
            .build();
          memberRepository.save(member);
        Member findMember = memberRepository.findByEmail("changho@naver.com").orElse(null);
        System.out.println("findMember = " + findMember.getEmail());

        //when



    //then


    }

}