package com.nextjob.base.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    BAD_REQUEST(40000, HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    NOT_FOUND(40400, HttpStatus.NOT_FOUND, "찾을 수 없습니다."),
    INTERNAL_SERVER_ERROR(50000, HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류입니다."),
    MEMBER_NOT_FOUND(40401, HttpStatus.NOT_FOUND, "해당 프로젝트의 참여 인원이 존재하지 않습니다."),
    CONFLICT(40900, HttpStatus.CONFLICT, "중복된 요청입니다."),
    CONFLICT_APPLY(40901, HttpStatus.CONFLICT, "이미 지원한 게시글입니다.");

    private final Integer code;
    private final HttpStatus httpStatus;
    private final String message;

    ErrorCode(Integer code, HttpStatus httpStatus, String message) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }
}