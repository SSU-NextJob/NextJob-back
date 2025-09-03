package com.nextjob.back.project.service.impl;

import com.nextjob.back.drive.service.DriveMapper;
import com.nextjob.back.kanbanBoard.domain.KanbanBoard;
import com.nextjob.back.kanbanBoard.service.KanbanBoardMapper;
import com.nextjob.back.notification.domain.Notification;
import com.nextjob.back.notification.service.NotificationMapper;
import com.nextjob.back.project.domain.Project;
import com.nextjob.back.project.service.ProjectMapper;
import com.nextjob.back.project.service.ProjectService;
import com.nextjob.back.project.web.ProjectSearchCriteria;
import com.nextjob.back.project.web.ProjectUserResponse;
import com.nextjob.back.workspace.domain.Workspace;
import com.nextjob.back.workspace.service.WorkspaceMapper;
import com.nextjob.base.exception.CustomException;
import com.nextjob.base.exception.ErrorCode;
import com.nextjob.base.util.CamelCaseMap;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private ProjectMapper projectMapper;
    private NotificationMapper notificationMapper;
    private WorkspaceMapper workspaceMapper;
    private KanbanBoardMapper kanbanBoardMapper;
    private DriveMapper driveMapper;

    public ProjectServiceImpl(ProjectMapper projectMapper, NotificationMapper notificationMapper, WorkspaceMapper workspaceMapper, KanbanBoardMapper kanbanBoardMapper, DriveMapper driveMapper) {
        this.projectMapper = projectMapper;
        this.notificationMapper = notificationMapper;
        this.workspaceMapper = workspaceMapper;
        this.kanbanBoardMapper = kanbanBoardMapper;
        this.driveMapper = driveMapper;
    }

    @Override
    public int insertProject(Project project) {
        // 1. 프로젝트 생성
        int count =  projectMapper.insertProject(project);

        // 2. 멤버에 팀장을 추가
        projectMapper.insertMember(project.getProjectId(), project.getCreatorId(), "LEADER");

        // 3. 워크스페이스 생성
        Workspace workspace = new Workspace();
        workspace.setProjectId(project.getProjectId());
        workspaceMapper.insertWorkSpace(workspace);

        // 4. 칸반보드 생성
        KanbanBoard kanbanBoard = new KanbanBoard();
        kanbanBoard.setWorkspaceId(workspace.getWorkspaceId());
        kanbanBoardMapper.insertKanbanBoard(kanbanBoard);
        // 4-1. 칸반 기본 컬럼 생성
        kanbanBoardMapper.insertKanbanDefaultColumn(kanbanBoard.getKanbanId());

        // 5. 드라이브 생성
        String drivePath = Integer.toString(workspace.getWorkspaceId()); // TODO: 드라이브 위치 지정
        driveMapper.insertDrive(workspace.getWorkspaceId(), drivePath);

        return count;
    }

    @Override
    public CamelCaseMap findProjectDetail(int projectId) {
        return projectMapper.findProjectDetail(projectId);
    }

    @Override
    public boolean applyProject(int projectId, int userId, int postId) {
        String requestType = "APPLY";       // 요청 종류 : 지원
        String requestStatus = "PENDING";   // 요청 상태 : 대기

        // 같은 게시글 중복 지원인지 확인
        int checkApply = projectMapper.findPostApplyMember(userId, postId);
        if (checkApply > 0) {
            throw new CustomException(ErrorCode.CONFLICT_APPLY);
        }

        // 0. 이미 참여 중인 프로젝트인지 확인
        int checkMemer = projectMapper.selectMemberByUserId(projectId, userId);
        if(checkMemer > 0) {
            throw new CustomException(ErrorCode.CONFLICT_PROJECT);
        }

        // 1. 요청 등록
        boolean successRequest = projectMapper.insertApplyRequest(projectId, userId, postId, requestType, requestStatus) > 0;

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

    @Override
    public void insertProjectMember(int projectId, int userId, String jobTitle) {
        projectMapper.insertMember(projectId, userId, jobTitle);
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

    /**
     * 프로젝트 지원한 사용자 목록 조회
     *
     * @param projectId
     * @return List<ProjectUserResponse>
     */
    @Override
    public List<ProjectUserResponse> findProjectApplyMemberList(int projectId) {
        return projectMapper.selectApplyMemberList(projectId);
    }

    /**
     * 프로젝트 참여인원 조회
     *
     * @param projectId
     * @return
     */
    @Override
    public List<CamelCaseMap> findProjectMemberList(int projectId) {
        return projectMapper.findProjectMemberList(projectId);
    }

    /**
     * 프로젝트 제거
     *
     * @param projectId
     * @return
     */
    @Override
    public int deleteProject(int projectId) {
        return projectMapper.deleteProject(projectId);
    }

    /**
     * 프로젝트 수정
     *
     * @param project
     * @return
     */
    @Override
    public int updateProject(Project project) {
        return projectMapper.updateProject(project);
    }
}
