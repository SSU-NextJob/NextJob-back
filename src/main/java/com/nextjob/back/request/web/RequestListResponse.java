package com.nextjob.back.request.web;

import com.nextjob.back.project.web.ProjectListResponse;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RequestListResponse {
    private int requestId;
    private String requestStatus;
    private LocalDateTime requestDate;
    private ProjectListResponse project;
}
