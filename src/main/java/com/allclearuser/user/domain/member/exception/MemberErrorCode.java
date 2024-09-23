package com.allclearuser.user.domain.member.exception;


import com.allclearuser.user.global.apiPayload.ApiResponse;
import com.allclearuser.user.global.apiPayload.code.status.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    // Block ERROR 응답
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND,
            "MEMBER404", "해당 id 사용자를 찾을 수 없습니다."),
    MEMBER_PASSWORD(HttpStatus.NOT_FOUND,"MEMBER405", "비밀번호가 일치하지 않습니다."),
    MEMBER_ALREADY_EXIST(HttpStatus.FOUND, "MEMBER406", "사용할 수 없는 id 입니다.")
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ApiResponse<Void> getErrorResponse() {
        return ApiResponse.onFailure(code, message);
    }
}

