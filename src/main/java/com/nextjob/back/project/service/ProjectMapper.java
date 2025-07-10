package com.nextjob.back.project.service;

import com.nextjob.back.project.domain.Project;
import com.nextjob.base.util.CamelCaseMap;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProjectMapper {
    /* 프로젝트 생성 */
    int insertProject(Project project);
    /* 프로젝트 상세 조회 */
    CamelCaseMap findProjectDetail(int projectId);
    /* 프로젝트 팀장 조회 */
    int findProjectCreatorId(int projectId);
    /* 프로젝트 지원 */
    int insertApplyRequest(int projectId, int userId, String requestType, String requestStatus);
    /* 프로젝트 멤버 추가 */
    void insertMember(int projectId, int userId, String jobTitle);
}