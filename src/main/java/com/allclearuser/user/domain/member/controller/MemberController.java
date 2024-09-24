package com.allclearuser.user.domain.member.controller;

import com.allclearuser.user.domain.auth.service.AuthService;
import com.allclearuser.user.domain.member.dto.response.LoginResponseDTO;
import com.allclearuser.user.domain.member.dto.response.StudentResponseDTO;
import com.allclearuser.user.domain.member.dto.request.LoginRequestDTO;
import com.allclearuser.user.domain.member.dto.request.SignupRequestDTO;
import com.allclearuser.user.domain.member.exception.MemberErrorCode;
import com.allclearuser.user.domain.member.exception.MemberException;
import com.allclearuser.user.domain.member.service.MemberCommandService;
import com.allclearuser.user.domain.member.service.MemberQueryService;
import com.allclearuser.user.global.apiPayload.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/users/")
public class MemberController {

    final private MemberQueryService memberQueryService;
    final private MemberCommandService memberCommandService;
    final private AuthService authService;

    @PostMapping("/login")
    public ApiResponse<LoginResponseDTO.GetMemberDTO> login(
            @RequestBody LoginRequestDTO.GetMemberDTO loginDto) {
        return ApiResponse.onSuccess(memberCommandService.login(loginDto));
    }

    @PostMapping("/signup")
    public ApiResponse<Boolean> signUp(@RequestBody SignupRequestDTO.CreateMemberDTO signupDto){
        return ApiResponse.onSuccess(memberCommandService.signUp(signupDto));
    }

    //토큰 재발급
    @GetMapping("/reissue")
    public ApiResponse<LoginResponseDTO.GetMemberDTO> createRefreshToken(HttpServletRequest request ){
        String accessToken = request.getHeader("Authorization");
        String refreshToken = request.getHeader("Refresh-Token");

        if (StringUtils.hasText(accessToken) && accessToken.startsWith("Bearer ") && StringUtils.hasText(refreshToken) && refreshToken.startsWith("Bearer ")) {
            LoginResponseDTO.GetMemberDTO result = authService.regenerateAccessToken(accessToken.substring(7), refreshToken.substring(7));
            return ApiResponse.onSuccess(result);
        } else
            throw new MemberException(MemberErrorCode.TOKEN_EMPTY);
    }

    @GetMapping("/student-info")
    public StudentResponseDTO.GetStudentDTO getStudentInfo(@RequestParam(name = "studentId") Long memberId){
        return memberQueryService.getStudentInfo(memberId);
    }

    @GetMapping("/profiles")
    public StudentResponseDTO.GetProfileDTO getProfile(@RequestHeader("studentId") Long studentId){
        return memberQueryService.getProfile(studentId);
    }
}
