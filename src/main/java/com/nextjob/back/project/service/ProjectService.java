package com.nextjob.back.project.service;

import com.nextjob.back.project.domain.Project;
import com.nextjob.base.util.CamelCaseMap;

public interface ProjectService {
    int insertProject(Project project);
    CamelCaseMap findProjectDetail(int projectId);
    boolean applyProject(int projectId, int userId);
    void insertProjectMember(int projectId, int userId, String jobTitle);
}
