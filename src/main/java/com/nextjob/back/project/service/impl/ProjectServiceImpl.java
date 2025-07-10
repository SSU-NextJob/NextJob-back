package com.nextjob.back.project.service.impl;

import com.nextjob.back.notification.domain.Notification;
import com.nextjob.back.notification.service.NotificationMapper;
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
    private NotificationMapper notificationMapper;

    public ProjectServiceImpl(ProjectMapper projectMapper, NotificationMapper notificationMapper) {
        this.projectMapper = projectMapper;
        this.notificationMapper = notificationMapper;
    }

    @Override
    public int insertProject(Project project) {
        // 1. 프로젝트 생성
        int count =  projectMapper.insertProject(project);

        // 2. 멤버에 팀장을 추가
        projectMapper.insertMember(project.getProjectId(), project.getCreatorId(), "LEADER");

        return count;
    }

    @Override
    public CamelCaseMap findProjectDetail(int projectId) {
        return projectMapper.findProjectDetail(projectId);
    }

    @Override
    public boolean applyProject(int projectId, int userId) {
        String requestType = "APPLY";       // 요청 종류 : 지원
        String requestStatus = "PENDING";   // 요청 상태 : 대기

        // 0. 기존 요청이 있는지 확인
        int checkMemer = projectMapper.selectMemberByUserId(projectId, userId);
        if(checkMemer > 0) {
            return false;
        }

        // 1. 요청 등록
        boolean successRequest = projectMapper.insertApplyRequest(projectId, userId, requestType, requestStatus) > 0;

        // 2. 프로젝트 팀장 조회
        int creatorId = projectMapper.findProjectCreatorId(projectId);

        // 3. 알림 등록
        Notification notification = new Notification();
        notification.setTitle("프로젝트 지원 요청");
        notification.setContent("프로젝트 지원을 요청드립니다.");
        notification.setNotificationType("APPLY_NOTIFICATION");
        notification.setUserId(creatorId);
        boolean successNotification = notificationMapper.insertNotification(notification) > 0;

        return successRequest;
    }

    /**
     * 생성한 프로젝트 조회
     *
     * @param projectSearchCriteria
     * @return
     */
    @Override
    public List<CamelCaseMap> findCreateProjectList(ProjectSearchCriteria projectSearchCriteria) {
        return projectMapper.findCreateProjectList(projectSearchCriteria);
    }

    /**
     * 참여한 프로젝트 조회
     *
     * @param projectSearchCriteria
     * @return
     */
    @Override
    public List<CamelCaseMap> findParticipationProjectList(ProjectSearchCriteria projectSearchCriteria) {
        return projectMapper.findParticipationProjectList(projectSearchCriteria);
    }
}
