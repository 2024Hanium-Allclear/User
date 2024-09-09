package com.allclearuser.user.domain.member.converter;


import com.allclearuser.user.domain.member.dto.SignupDto;
import com.allclearuser.user.domain.member.dto.StudentDto;
import com.allclearuser.user.domain.member.entity.Student;

public class MemberConverter {
    public static Student toEntity(SignupDto.RequestDto requestDto, String encodedPw){
        return Student.builder()
                .grade(requestDto.getGrade())
                .studentIdNumber(requestDto.getStudentIdNumber())
                .loginId(requestDto.getLoginId())
                .password(encodedPw)
                .build();
    }
    public static StudentDto toDto(Student student) {

        return StudentDto.builder()
                .id(student.getId()).build();
    }

}
