package com.allclearuser.user.domain.member.controller;

import com.allclearuser.user.domain.member.dto.response.StudentResponseDTO;
import com.allclearuser.user.domain.member.dto.request.LoginRequestDTO;
import com.allclearuser.user.domain.member.dto.request.SignupRequestDTO;
import com.allclearuser.user.domain.member.service.MemberCommandService;
import com.allclearuser.user.domain.member.service.MemberQueryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
public class MemberController {

    final private MemberQueryService memberQueryService;
    final private MemberCommandService memberCommandService;

    @PostMapping("/login")
    public ResponseEntity<StudentResponseDTO.GetStudentDTO> login(
            @RequestBody LoginRequestDTO.GetMemberDTO loginDto) {
        return ResponseEntity.ok(memberCommandService.login(loginDto));
    }

    @PostMapping("/signup")
    public ResponseEntity<Boolean> signUp(@RequestBody SignupRequestDTO.CreateMemberDTO signupDto){
        return ResponseEntity.ok(memberQueryService.signUp(signupDto));
    }

//    @PostMapping("/refreshToken")
//    public ResponseEntity<Boolean> updateRefreshToken(@RequestBody TokenDto.UpdateRefreshTokenRequestDto updateRefreshTokenRequestDto){
//        return ResponseEntity.ok(memberService.updateRefreshToken(updateRefreshTokenRequestDto));
//    }
//
//    @GetMapping("/student/info/{memberId}")
//    public ResponseEntity<StudentDto> getStudentInfo(@PathVariable(name = "memberId") Long memberId){
//        return ResponseEntity.ok(memberService.getStudentInfo(memberId));
//    }
}
