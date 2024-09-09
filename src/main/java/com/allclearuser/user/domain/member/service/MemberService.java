package com.allclearuser.user.domain.member.service;


import com.allclearuser.user.domain.member.converter.MemberConverter;
import com.allclearuser.user.domain.member.dto.LoginDto;
import com.allclearuser.user.domain.member.dto.SignupDto;
import com.allclearuser.user.domain.member.dto.StudentDto;
import com.allclearuser.user.domain.member.dto.TokenDto;
import com.allclearuser.user.domain.member.entity.Student;
import com.allclearuser.user.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    public StudentDto login(LoginDto.RequestDto loginDto) {
        //비밀번호 비번
        Student student = memberRepository.findByLoginId(loginDto.getLoginId());
        if(student == null){
            return null;
        }

        boolean checkPw = passwordEncoder.matches(loginDto.getPassword(), student.getPassword());
        if (checkPw) {
            return MemberConverter.toDto(student);
        }
        //TODO 에러처리 필요
        return null;
    }

    public Boolean signUp(SignupDto.RequestDto signupDto) {
        //TODO 에러처리 핸들ㄹ로 변경
        if(memberRepository.findByLoginId(signupDto.getLoginId()) != null){
            return false;
        }
        String pw = signupDto.getPassword();
        String encodedPw = passwordEncoder.encode(pw);

        Student member = MemberConverter.toEntity(signupDto, encodedPw);
        memberRepository.save(member);

        return true;
    }

    @Transactional
    public Boolean updateRefreshToken(TokenDto.UpdateRefreshTokenRequestDto updateRefreshTokenRequestDto) {
        //TODO 에러 핸들러를 통한 처리 필요
        Student student = memberRepository.findById(updateRefreshTokenRequestDto.getId()).get();
        student.updateRefreshToken(updateRefreshTokenRequestDto.getRefreshToken());

        return true;
    }

    public StudentDto getStudentInfo(Long memberId) {
        //TODO 에러 핸들러를 통한 처리 필요
        Student student = memberRepository.findById(memberId).get();

        //TODO 필요한 필드에 따라 DTO 변결 필요, id 뿐만아니라 학교 학점 등등
        return MemberConverter.toDto(student);
    }
}
