package com.allclearuser.user.domain.member.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StudentResponseDTO {
    @Getter
    @Builder
    public static class GetStudentDTO {
        private Long studentId;
        private String studentIdNumber;
        private int grade;
    }

    @Getter
    @Builder
    public static class GetProfileDTO {
        private Long studentId;
        private String studentIdNumber;
        private int grade;

    }
}
