package com.changddao.junhada.api;

import com.changddao.junhada.entity.Address;
import com.changddao.junhada.entity.Authority;
import com.changddao.junhada.entity.Member;
import com.changddao.junhada.jwt.JwtProvider;
import com.changddao.junhada.repository.AuthorityRepository;
import com.changddao.junhada.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@Transactional
@RequiredArgsConstructor
public class SignService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public SignResponse login(SignRequestDto request)throws Exception{
        Member member = memberRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new BadCredentialsException("잘못된 계정 정보입니다.")
        );
        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new BadCredentialsException("잘못된 계정 정보입니다.");
        }
        return SignResponse.builder()
                .id(member.getId())
                .email(member.getEmail())
                .nickName(member.getNickName())
                .address(member.getAddress())
                .roles(member.getRoles())
                .token(jwtProvider.createToken(member.getEmail(), member.getRoles()))
                .build();
    }
    public boolean register(SignRequestDto request)throws Exception {
        try {
            Address adr = Address.builder()
                    .zipcode(request.getZipcode())
                    .city(request.getCity())
                    .street(request.getStreet())
                    .detail(request.getDetail())
                    .build();
            Member member = Member.builder()
                    .email(request.getEmail())
                    .nickName(request.getNickName())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .address(adr)
                    .build();

            member.setRoles(Collections.singletonList(Authority.builder().memberRole("ROLE_USER").build()));
            memberRepository.save(member);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("잘못된 요청입니다.");
        }
        return true;
    }
    public SignResponse getMember(String email) throws Exception{
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new Exception("계정을 찾을 수 없습니다."));
        return new SignResponse(member);
    }
}
