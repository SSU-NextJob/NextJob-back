package com.nextjob.back.project.web;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProjectListResponse {
    private int projectId;
    private String name;
    private String content;
    private String type;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String image;
    private String status;
}
