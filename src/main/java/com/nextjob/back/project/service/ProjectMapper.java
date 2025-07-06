package com.nextjob.back.project.service;

import com.nextjob.back.project.domain.Project;
import com.nextjob.back.project.web.ProjectSearchCriteria;
import com.nextjob.back.user.web.UserSearchCriteria;
import com.nextjob.base.util.CamelCaseMap;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface ProjectMapper {
    /* 프로젝트 생성 */
    int insertProject(Project project);
    /* 프로젝트 상세 조회 */
    CamelCaseMap findProjectDetail(int projectId);
}