package com.nextjob.back.project.web;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ProjectListResponse {
    private int projectId;
    private String name;
    private String content;
    private String projectType;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String image;
}
