package com.nextjob.back.project.service;

import com.nextjob.back.project.domain.Project;
import com.nextjob.back.project.web.ProjectSearchCriteria;
import com.nextjob.back.project.web.ProjectUserResponse;
import com.nextjob.base.util.CamelCaseMap;

import java.util.List;

public interface ProjectService {
    int insertProject(Project project);

    CamelCaseMap findProjectDetail(int projectId);

    boolean applyProject(int projectId, int userId);

    void insertProjectMember(int projectId, int userId, String jobTitle);

    /* 내가 생성한 프로젝트 조회 */
    List<CamelCaseMap> findCreateProjectList(ProjectSearchCriteria projectSearchCriteria);

    /* 내가 참여한 프로젝트 조회 */
    List<CamelCaseMap> findParticipationProjectList(ProjectSearchCriteria projectSearchCriteria);

    /* 프로젝트 지원한 사용자 목록 조회 */
    List<ProjectUserResponse> findProjectApplyMemberList(int projectId);

    /* 프로젝트 참여 인원 조회 */
    List<CamelCaseMap> findProjectMemberList(int projectId);
}
