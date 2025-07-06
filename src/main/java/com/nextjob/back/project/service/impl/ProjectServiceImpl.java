package com.nextjob.back.project.service.impl;

import com.nextjob.back.project.domain.Project;
import com.nextjob.back.project.service.ProjectMapper;
import com.nextjob.back.project.service.ProjectService;
import com.nextjob.back.project.web.ProjectSearchCriteria;
import com.nextjob.base.util.CamelCaseMap;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private ProjectMapper projectMapper;

    public ProjectServiceImpl(ProjectMapper projectMapper) {
        this.projectMapper = projectMapper;
    }

    @Override
    public int insertProject(Project project) {
        return projectMapper.insertProject(project);
    }

    @Override
    public CamelCaseMap findProjectDetail(int projectId) {
        return projectMapper.findProjectDetail(projectId);
    }
}
