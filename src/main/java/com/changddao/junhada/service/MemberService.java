package com.changddao.junhada.service;

import com.changddao.junhada.entity.LaptopBoard;
import com.changddao.junhada.entity.Member;
import com.changddao.junhada.repository.MemberRepository;
import com.changddao.junhada.repository.laptop.LaptopBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    MemberRepository memberRepository;
    LaptopBoardRepository laptopBoardRepository;

    //회원가입
    public Long memberJoin(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }
    //중복회원 검증 logic
    public void validateDuplicateMember(Member member) {
        List<Member> findMemberByEmail = memberRepository.findByEmail(member.getEmail());
        if(!findMemberByEmail.isEmpty())
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        else return;
    }

    //회원이 작성한 글 불러오기 ex)laptopBoard
    public List<LaptopBoard> laptopBoardsByMember(Long memberId){
        List<LaptopBoard> laptopBoards = laptopBoardRepository.laptopBoardsByMember(memberId);
        return laptopBoards;



    }





}
