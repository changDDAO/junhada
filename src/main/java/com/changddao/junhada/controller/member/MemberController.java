package com.changddao.junhada.controller.member;

import com.changddao.junhada.controller.MsgAlert;
import com.changddao.junhada.entity.Address;
import com.changddao.junhada.entity.Authority;
import com.changddao.junhada.entity.Member;
import com.changddao.junhada.jwt.JwtProvider;
import com.changddao.junhada.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Collections;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    @GetMapping("/members/new")
    public String memberForm(Model model){
        model.addAttribute("memberForm",new MemberForm());
        return "members/createMemberForm";
    }
    @PostMapping("/members/new")
    public String createMember(@Valid MemberForm memberForm, BindingResult result, Model model){
        if(result.hasErrors()) return "members/createMemberForm";

        Address address = new Address(memberForm.getZipcode(), memberForm.getCity()
                , memberForm.getStreet(), memberForm.getDetail());
        Member member = new Member(memberForm.getEmail(), memberForm.getNickName(),
                passwordEncoder.encode(memberForm.getPassword()), address);
        member.setRoles(Collections.singletonList(Authority.builder().name("ROLE_USER").build()));
        memberService.memberJoin(member);
        model.addAttribute("data",new MsgAlert("회원가입이 완료되었습니다.","/"));
        return "message";
    }
    @GetMapping("/members/login")
    public String memberLoginForm(Model model){
        model.addAttribute("loginForm",new MemberForm());
        return "members/memberLoginForm";
    }
    @PostMapping("/members/login")
    public String login(@Valid LoginForm loginForm, BindingResult result, Model model) {
        if(result.hasErrors()) return "members/memberLoginForm";
        MemberSignResponse passedMember = memberService.login(loginForm);
        System.out.println("email"+passedMember.getEmail());
        System.out.println("token"+passedMember.getToken());
        System.out.println(jwtProvider.getAccount(passedMember.getToken()));
        model.addAttribute("passedMember", passedMember);
        return "home";
    }

}
