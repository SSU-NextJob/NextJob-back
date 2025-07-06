package com.nextjob.back.project.service;

import com.nextjob.back.project.domain.Project;
import com.nextjob.back.project.web.ProjectSearchCriteria;
import com.nextjob.base.util.CamelCaseMap;

import java.util.List;

public interface ProjectService {
    int insertProject(Project project);
    CamelCaseMap findProjectDetail(int projectId);
}
