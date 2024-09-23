package com.allclearuser.user.domain.member.converter;


import com.allclearuser.user.domain.member.dto.response.StudentResponseDTO;
import com.allclearuser.user.domain.member.dto.request.SignupRequestDTO;
import com.allclearuser.user.domain.member.entity.Student;

public class MemberConverter {
    public static Student toEntity(SignupRequestDTO.CreateMemberDTO requestDto, String encodedPw){
        return Student.builder()
                .grade(requestDto.getGrade())
                .studentIdNumber(requestDto.getStudentIdNumber())
                .loginId(requestDto.getLoginId())
                .password(encodedPw)
                .build();
    }
    public static StudentResponseDTO.GetStudentDTO from(Student student) {

        return StudentResponseDTO.GetStudentDTO.builder()
                .id(student.getId()).build();
    }

}
