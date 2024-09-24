package com.allclearuser.user.domain.member.service;

import com.allclearuser.user.domain.auth.service.AuthService;
import com.allclearuser.user.domain.member.converter.MemberConverter;
import com.allclearuser.user.domain.member.dto.request.SignupRequestDTO;
import com.allclearuser.user.domain.member.dto.response.LoginResponseDTO;
import com.allclearuser.user.domain.member.dto.response.StudentResponseDTO;
import com.allclearuser.user.domain.member.dto.request.LoginRequestDTO;
import com.allclearuser.user.domain.member.entity.Student;
import com.allclearuser.user.domain.member.exception.MemberErrorCode;
import com.allclearuser.user.domain.member.exception.MemberException;
import com.allclearuser.user.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberCommandService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final AuthService authService;

    public Boolean signUp(SignupRequestDTO.CreateMemberDTO signupDto) {

        if(memberRepository.findByLoginId(signupDto.getLoginId()) != null){
            throw new MemberException(MemberErrorCode.MEMBER_ALREADY_EXIST);
        }
        String pw = signupDto.getPassword();
        String encodedPw = passwordEncoder.encode(pw);

        Student member = MemberConverter.toEntity(signupDto, encodedPw);
        memberRepository.save(member);

        return true;
    }

    @Transactional
    public LoginResponseDTO.GetMemberDTO login(LoginRequestDTO.GetMemberDTO loginDto) {
        //비밀번호 비번
        Student student = memberRepository.findByLoginId(loginDto.getLoginId());
        if(student == null){
            throw new MemberException(MemberErrorCode.MEMBER_NOT_FOUND);
        }

        boolean checkPw = passwordEncoder.matches(loginDto.getPassword(), student.getPassword());
        if (checkPw) {
            return authService.generateToken(student); // 토큰 발급
        }
        else{
            throw new MemberException(MemberErrorCode.MEMBER_PASSWORD);
        }
    }


}