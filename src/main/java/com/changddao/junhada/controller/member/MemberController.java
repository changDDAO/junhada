package com.changddao.junhada.controller.member;

import com.changddao.junhada.controller.MsgAlert;
import com.changddao.junhada.entity.Address;
import com.changddao.junhada.entity.Member;
import com.changddao.junhada.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
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
                memberForm.getPassword(), address);

        memberService.memberJoin(member);
        model.addAttribute("data",new MsgAlert("회원가입이 완료되었습니다.","/"));
        return "message";
    }
}
