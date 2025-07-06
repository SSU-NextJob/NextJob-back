package com.nextjob.base.web.response;

import com.nextjob.base.exception.ErrorCode;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ExceptionDto {
    @NotNull
    private final Integer code;

    @NotNull
    private final String message;

    // 기존 생성자
    public ExceptionDto(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    // 커스텀 메시지 받는 생성자 추가
    public ExceptionDto(ErrorCode errorCode, String message) {
        this.code = errorCode.getCode();
        this.message = message;
    }

    // 기존 of 메서드 유지
    public static ExceptionDto of(ErrorCode errorCode) {
        return new ExceptionDto(errorCode);
    }

    // 커스텀 메시지 받는 of 메서드 추가
    public static ExceptionDto of(ErrorCode errorCode, String message) {
        String finalMessage = (message == null || message.isEmpty()) ? errorCode.getMessage() : message;
        return new ExceptionDto(errorCode, finalMessage);
    }
}
