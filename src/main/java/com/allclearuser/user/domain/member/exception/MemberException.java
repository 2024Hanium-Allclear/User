package com.allclearuser.user.domain.member.exception;


import com.allclearuser.user.global.apiPayload.code.status.BaseErrorCode;
import com.allclearuser.user.global.apiPayload.exception.GeneralException;
import lombok.Getter;

@Getter
public class MemberException extends GeneralException {
    public MemberException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}