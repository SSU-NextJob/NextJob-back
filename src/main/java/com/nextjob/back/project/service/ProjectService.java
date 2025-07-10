package com.nextjob.back.project.service;

import com.nextjob.back.project.domain.Project;
import com.nextjob.back.project.web.ProjectSearchCriteria;
import com.nextjob.base.util.CamelCaseMap;

import java.util.List;

public interface ProjectService {
    int insertProject(Project project);
    CamelCaseMap findProjectDetail(int projectId);
    boolean applyProject(int projectId, int userId);

    /* 내가 생성한 프로젝트 조회 */
    List<CamelCaseMap> findCreateProjectList(ProjectSearchCriteria projectSearchCriteria);

    /* 내가 참여한 프로젝트 조회 */
    List<CamelCaseMap> findParticipationProjectList(ProjectSearchCriteria projectSearchCriteria);

}
