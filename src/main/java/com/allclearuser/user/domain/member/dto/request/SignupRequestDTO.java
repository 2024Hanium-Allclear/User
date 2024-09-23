package com.allclearuser.user.domain.member.dto.request;

import lombok.Builder;
import lombok.Getter;

public class SignupRequestDTO {

    @Builder
    @Getter
    public static class CreateMemberDTO{
        private String loginId;
        private String password;
        private String studentIdNumber;
        private int grade;
    }
}
