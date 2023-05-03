package com.changddao.junhada.api;

import com.changddao.junhada.entity.Address;
import com.changddao.junhada.entity.Authority;
import com.changddao.junhada.entity.Member;
import com.changddao.junhada.repository.AuthorityRepository;
import com.changddao.junhada.repository.MemberRepository;
import com.changddao.junhada.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class SignServiceTest {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberService memberService;
    @Autowired
    AuthorityRepository authorityRepository;

    @Test
    @Transactional
    public void register() {

        //given
        Member member = Member.builder()
                .email("changho@naver.com")
                .address(new Address("422", "daegu", "chungsuro", "castle"))
                .nickName("changddao")
                .password("password")
                .build();
        memberService.memberJoin(member);

        Member findMember = memberRepository.findByEmail("changho@naver.com").orElse(null);
        System.out.println("findMember = " + findMember.getEmail());
        System.out.println("findMemberId = " + findMember.getId());
        Authority memberRole = Authority.builder().memberRole("ROLE_USER")
                .member(findMember)
                .build();
        authorityRepository.save(memberRole);
        List<Authority> all = authorityRepository.findAll();
        for (Authority authority : all) {
            System.out.println("authority.getMemberRole() = " + authority.getMemberRole());
            System.out.println("authority.getMemberRole() = " + authority.getMember().getEmail());

        }

    }








        //when



    //then
}

