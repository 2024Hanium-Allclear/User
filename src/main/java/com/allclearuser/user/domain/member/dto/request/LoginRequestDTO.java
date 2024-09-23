package com.allclearuser.user.domain.member.dto.request;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class LoginRequestDTO {
    @Getter
    @Builder
    @Setter
    public static class GetMemberDTO{
        private String loginId;
        private String password;
    }

    @Getter
    @Builder
    public static class GetAccessTokenDTO{
        private Long id;

        String accessToken;
    }

    @Getter
    @Builder
    public static class UpdateRefreshTokenDTO{
        private Long id;

        String refreshToken;
    }
}
