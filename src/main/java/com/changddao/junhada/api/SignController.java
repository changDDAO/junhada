package com.changddao.junhada.api;

import com.changddao.junhada.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class SignController {

    private final MemberRepository memberRepository;
    private final SignService signService;

    @PostMapping("/login")
    public ResponseEntity<SignResponse> signIn(@RequestBody SignRequestDto request)
        throws Exception{
        return new ResponseEntity<>(signService.login(request), HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<Boolean> signUp(@RequestBody SignRequestDto request)
            throws Exception{
        return new ResponseEntity<>(signService.register(request), HttpStatus.OK);
    }
    @GetMapping("/user/get")
    public ResponseEntity<SignResponse> getUser(@RequestParam String email)
        throws Exception{
        return new ResponseEntity<>(signService.getMember(email), HttpStatus.OK);
    }

    @GetMapping("/admin/get")
    public ResponseEntity<SignResponse> getUserForAdmin(@RequestParam String email)
        throws Exception{
        return new ResponseEntity<>(signService.getMember(email), HttpStatus.OK);
    }
}
