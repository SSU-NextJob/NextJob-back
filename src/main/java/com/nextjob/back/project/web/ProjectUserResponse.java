package com.nextjob.back.project.web;

import com.nextjob.base.web.servlet.search.Search;
import lombok.Getter;

@Getter
public class ProjectUserResponse implements Search {
    private int projectId;
    private int userId;
    private int postId;
    private String name;
    private String description;
    private String techStack;
    private String profileImage;
    private String userType;
    private String requestStatus;
}
