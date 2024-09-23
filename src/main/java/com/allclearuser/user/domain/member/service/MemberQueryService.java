package com.allclearuser.user.domain.member.service;

import com.allclearuser.user.domain.member.converter.MemberConverter;
import com.allclearuser.user.domain.member.dto.request.SignupRequestDTO;
import com.allclearuser.user.domain.member.entity.Student;
import com.allclearuser.user.domain.member.exception.MemberErrorCode;
import com.allclearuser.user.domain.member.exception.MemberException;
import com.allclearuser.user.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class MemberQueryService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    public Boolean signUp(SignupRequestDTO.CreateMemberDTO signupDto) {
        //TODO 에러처리 핸들ㄹ로 변경
        if(memberRepository.findByLoginId(signupDto.getLoginId()) != null){
            throw new MemberException(MemberErrorCode.MEMBER_ALREADY_EXIST);
        }
        String pw = signupDto.getPassword();
        String encodedPw = passwordEncoder.encode(pw);

        Student member = MemberConverter.toEntity(signupDto, encodedPw);
        memberRepository.save(member);

        return true;
    }

}
