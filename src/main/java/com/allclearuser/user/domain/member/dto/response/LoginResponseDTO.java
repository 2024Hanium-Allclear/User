package com.allclearuser.user.domain.member.dto.response;

import lombok.Builder;
import lombok.Getter;

public class LoginResponseDTO {

    @Getter
    @Builder
    public static class GetMemberDTO{
        String refreshToken;
        String accessToken;
    }
}
