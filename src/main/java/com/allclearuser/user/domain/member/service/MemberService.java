//package com.allclearuser.user.domain.member.service;
//
//
//import com.allclearuser.user.domain.member.repository.MemberRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//
//@RequiredArgsConstructor
//@Service
//@Slf4j
//public class MemberService {
//
//    private final MemberRepository memberRepository;
//    private final PasswordEncoder passwordEncoder;
////    public StudentDto login(LoginDto.RequestDto loginDto) {
////        //비밀번호 비번
////        Student student = memberRepository.findByLoginId(loginDto.getLoginId());
////        if(student == null){
////            return null;
////        }
////
////        boolean checkPw = passwordEncoder.matches(loginDto.getPassword(), student.getPassword());
////        if (checkPw) {
////            return MemberConverter.from(student);
////        }
////        return null;
////    }
//
//
////    @Transactional
////    public Boolean updateRefreshToken(TokenDto.UpdateRefreshTokenRequestDto updateRefreshTokenRequestDto) {
////        //TODO 에러 핸들러를 통한 처리 필요
////        Student student = memberRepository.findById(updateRefreshTokenRequestDto.getId()).get();
////        student.updateRefreshToken(updateRefreshTokenRequestDto.getRefreshToken());
////
////        return true;
////    }
////
////    public StudentDto getStudentInfo(Long memberId) {
////        //TODO 에러 핸들러를 통한 처리 필요
////        Student student = memberRepository.findById(memberId).get();
////
////        //TODO 필요한 필드에 따라 DTO 변결 필요, id 뿐만아니라 학교 학점 등등
////        return MemberConverter.toDto(student);
////    }
//}
