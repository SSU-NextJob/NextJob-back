package com.nextjob.base.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    BAD_REQUEST(40000, HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),

    ACCESS_TOKEN_NOT_FOUND(40100, HttpStatus.UNAUTHORIZED, "잘못된 액세스 토큰입니다."),

    NOT_FOUND(40400, HttpStatus.NOT_FOUND, "찾을 수 없습니다."),
    MEMBER_NOT_FOUND(40401, HttpStatus.NOT_FOUND, "해당 프로젝트의 참여 인원이 존재하지 않습니다."),
    PROJECT_NOT_FOUND(40402, HttpStatus.NOT_FOUND, "프로젝트를 찾을 수 없습니다."),
    POST_NOT_FOUND(40403, HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다."),
    USER_NOT_FOUND(40404, HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."),
    TASK_NOT_FOUND(40405, HttpStatus.NOT_FOUND, "작업 목록을 찾을 수 없습니다."),

    CONFLICT(40900, HttpStatus.CONFLICT, "중복된 요청입니다."),
    CONFLICT_APPLY(40901, HttpStatus.CONFLICT, "이미 지원한 게시글입니다."),
    CONFLICT_PROJECT(40902, HttpStatus.CONFLICT, "이미 참여 중인 프로젝트입니다."),

    INTERNAL_SERVER_ERROR(50000, HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류입니다."),
    INSERT_ERROR(50001, HttpStatus.INTERNAL_SERVER_ERROR, "저장에 실패하였습니다."),
    UPDATE_ERROR(50002, HttpStatus.INTERNAL_SERVER_ERROR, "수정에 실패하였습니다.");

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