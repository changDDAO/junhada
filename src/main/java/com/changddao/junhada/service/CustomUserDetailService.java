package com.changddao.junhada.service;

import com.changddao.junhada.entity.Member;
import com.changddao.junhada.jwt.CustomUserDetails;
import com.changddao.junhada.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email).orElseThrow(
                ()-> new UsernameNotFoundException("Invalid Authentication!")
        );
        return new CustomUserDetails(member);
    }
}
