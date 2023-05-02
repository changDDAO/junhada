package com.changddao.junhada.service;

import com.changddao.junhada.entity.laptop.LaptopBoard;
import com.changddao.junhada.entity.laptop.LaptopReply;
import com.changddao.junhada.entity.Member;
import com.changddao.junhada.repository.MemberRepository;
import com.changddao.junhada.repository.laptop.LaptopBoardRepository;
import com.changddao.junhada.repository.laptop.LaptopReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
   private final MemberRepository memberRepository;
    private final LaptopBoardRepository laptopBoardRepository;

    private final LaptopReplyRepository laptopReplyRepository;
   /* @Autowired
    public MemberService(MemberRepository memberRepository, LaptopBoardRepository laptopBoardRepository){
        this.memberRepository = memberRepository;
        this.laptopBoardRepository = laptopBoardRepository;
    }*/

    //회원가입
    public Long memberJoin(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }
    //중복회원 검증 logic //성공
    public void validateDuplicateMember(Member member) {
        Optional<Member> findMemberByEmail = memberRepository.findByEmail(member.getEmail());
        if(!findMemberByEmail.isEmpty())
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        else return;
    }

    //회원이 작성한 글 불러오기 ex)laptopBoard  //성공
    public List<LaptopBoard> laptopBoardsByMember(Long memberId){
        List<LaptopBoard> laptopBoards = laptopBoardRepository.laptopBoardsByMember(memberId);
        return laptopBoards;

    }

    //멤버 전체 조회 //성공
    public List<Member> findMembers(){
        List<Member> members = memberRepository.findAll();
        return members;
    }

    //멤버 단건 조회 //성공
    public Member findOneMember(Long id){
        Member findMember = memberRepository.findById(id).orElse(null);
        return findMember;
    }
    //멤버가 작성한 laptop댓글 불러오기 // 성공
    public List<LaptopReply> laptopRepliesByMember(Long memberId){
        List<LaptopReply> laptopRepliesByMember = laptopReplyRepository.findLaptopRepliesByMember(memberId);
        return laptopRepliesByMember;
    }






}
