package com.allclearuser.user;

import com.allclearuser.user.global.apiPayload.ApiResponse;
import com.allclearuser.user.global.apiPayload.code.GeneralErrorCode;
import com.allclearuser.user.global.apiPayload.code.status.BaseErrorCode;
import com.allclearuser.user.global.apiPayload.exception.GeneralException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/users")
@RestController
public class TestController {

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck(@RequestHeader("studentId") String studentId){
        return ResponseEntity.ok(studentId);
    }

    @GetMapping("/test1")
    public ApiResponse<String> test1() {
        return ApiResponse.onSuccess("handlerTest");
    }

    @GetMapping("/test2")
    public ApiResponse<String> test2() {
        throw new GeneralException(GeneralErrorCode.INTERNAL_SERVER_ERROR_500);
    }
}
