package com.allclearuser.user.domain.auth.service;

import com.allclearuser.user.domain.member.dto.response.LoginResponseDTO;
import com.allclearuser.user.domain.member.entity.Student;
import com.allclearuser.user.domain.member.exception.MemberErrorCode;
import com.allclearuser.user.domain.member.exception.MemberException;
import com.allclearuser.user.domain.member.jwt.JwtGenerator;
import com.allclearuser.user.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.allclearuser.user.domain.member.exception.MemberErrorCode.MEMBER_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class AuthService {

    final private JwtGenerator jwtGenerator;
    final private MemberRepository memberRepository;

    @Transactional
    public LoginResponseDTO.GetMemberDTO generateToken(Student student){
        String newAccessToken = jwtGenerator.generateAccessToken(student.getId());
        String newRefreshToken = jwtGenerator.generateRefreshToken(student.getId());


        //새로받은 refresh 토큰 변경
        student.updateRefreshToken(newRefreshToken);

//        memberRepository.save(student);

        return LoginResponseDTO.GetMemberDTO.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .build();
    }

    // refreshToken으로 accessToken 발급하기
    @Transactional
    public LoginResponseDTO.GetMemberDTO regenerateAccessToken(String accessToken, String refreshToken) {

        Long memberId = jwtGenerator.getMemberIdFromJwtToken(refreshToken);

        Student member = memberRepository.findById(memberId).orElseThrow(()-> new MemberException(MEMBER_NOT_FOUND));

        if(!refreshToken.equals(member.getRefreshToken()))
            throw new MemberException(MemberErrorCode.REFRESH_TOKEN_UNAUTHORIZED);

        String newRefreshToken = jwtGenerator.generateRefreshToken(memberId);
        String newAccessToken = jwtGenerator.generateAccessToken(memberId);

        member.updateRefreshToken(newRefreshToken);

        return LoginResponseDTO.GetMemberDTO.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .build();
    }
}
