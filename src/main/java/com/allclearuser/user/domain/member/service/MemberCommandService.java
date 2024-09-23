package com.allclearuser.user.domain.member.service;

import com.allclearuser.user.domain.member.converter.MemberConverter;
import com.allclearuser.user.domain.member.dto.response.StudentResponseDTO;
import com.allclearuser.user.domain.member.dto.request.LoginRequestDTO;
import com.allclearuser.user.domain.member.entity.Student;
import com.allclearuser.user.domain.member.exception.MemberErrorCode;
import com.allclearuser.user.domain.member.exception.MemberException;
import com.allclearuser.user.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberCommandService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    public StudentResponseDTO.GetStudentDTO login(LoginRequestDTO.GetMemberDTO loginDto) {
        //비밀번호 비번
        Student student = memberRepository.findByLoginId(loginDto.getLoginId());
        if(student == null){
            throw new MemberException(MemberErrorCode.MEMBER_NOT_FOUND);
        }

        boolean checkPw = passwordEncoder.matches(loginDto.getPassword(), student.getPassword());
        if (checkPw) {
            return MemberConverter.from(student);
        }
        else{
            throw new MemberException(MemberErrorCode.MEMBER_PASSWORD);
        }
    }
}