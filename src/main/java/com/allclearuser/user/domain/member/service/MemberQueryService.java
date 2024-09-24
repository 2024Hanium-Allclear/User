package com.allclearuser.user.domain.member.service;
import com.allclearuser.user.domain.member.converter.MemberConverter;
import com.allclearuser.user.domain.member.dto.response.StudentResponseDTO;
import com.allclearuser.user.domain.member.entity.Student;
import com.allclearuser.user.domain.member.exception.MemberErrorCode;
import com.allclearuser.user.domain.member.exception.MemberException;
import com.allclearuser.user.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class MemberQueryService {

    final private MemberRepository memberRepository;
    public StudentResponseDTO.GetStudentDTO getStudentInfo(Long memberId) {

        Student student = memberRepository.findById(memberId).orElseThrow(()->new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        return MemberConverter.from(student);
    }

    public StudentResponseDTO.GetProfileDTO getProfile(Long memberId) {
        Student student = memberRepository.findById(memberId).orElseThrow(()->new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        return MemberConverter.fromEntity(student);
    }
}
