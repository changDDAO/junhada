package com.changddao.junhada.service;

import com.changddao.junhada.controller.member.LoginForm;
import com.changddao.junhada.controller.member.MemberSignResponse;
import com.changddao.junhada.entity.laptop.LaptopBoard;
import com.changddao.junhada.entity.laptop.LaptopReply;
import com.changddao.junhada.entity.Member;
import com.changddao.junhada.jwt.JwtProvider;
import com.changddao.junhada.repository.MemberRepository;
import com.changddao.junhada.repository.laptop.LaptopBoardRepository;
import com.changddao.junhada.repository.laptop.LaptopReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
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
    //login하기
    public MemberSignResponse login(LoginForm request) {
        Member member = memberRepository.findByEmail(request.getEmail()).orElseThrow(
                ()-> new BadCredentialsException("잘못된 계정 정보입니다.")
        );
        if (passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new BadCredentialsException("잘못된 계정 정보입니다.");
        }
        return MemberSignResponse.builder()
                .memberId(member.getId())
                .nickName(member.getNickName())
                .email(member.getEmail())
                .roles(member.getRoles())
                .token(jwtProvider.createToken(member.getEmail(), member.getRoles()))
                .build();

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
