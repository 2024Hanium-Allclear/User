package com.allclearuser.user.domain.member.controller;

import com.allclearuser.user.domain.member.dto.SignupDto;
import com.allclearuser.user.domain.member.dto.LoginDto;
import com.allclearuser.user.domain.member.dto.StudentDto;
import com.allclearuser.user.domain.member.dto.TokenDto;
import com.allclearuser.user.domain.member.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
public class MemberController {

    final private MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity<StudentDto> login(
            @RequestBody LoginDto.RequestDto loginDto) {
        return ResponseEntity.ok(memberService.login(loginDto));
    }

    @PostMapping("/signup")
    public ResponseEntity<Boolean> signUp(@RequestBody SignupDto.RequestDto signupDto){
        return ResponseEntity.ok(memberService.signUp(signupDto));
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<Boolean> updateRefreshToken(@RequestBody TokenDto.UpdateRefreshTokenRequestDto updateRefreshTokenRequestDto){
        return ResponseEntity.ok(memberService.updateRefreshToken(updateRefreshTokenRequestDto));
    }
//
    @GetMapping("/student/info/{memberId}")
    public ResponseEntity<StudentDto> getStudentInfo(@PathVariable(name = "memberId") Long memberId){
        return ResponseEntity.ok(memberService.getStudentInfo(memberId));
    }
}
