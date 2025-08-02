package com.nextjob.back.project.web;

import com.nextjob.back.project.domain.Project;
import com.nextjob.back.project.service.ProjectService;
import com.nextjob.base.exception.CustomException;
import com.nextjob.base.exception.ErrorCode;
import com.nextjob.base.util.CamelCaseMap;
import com.nextjob.base.web.response.ApiResponse;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    /**
     * 프로젝트 생성
     *
     * @param project
     * @return
     */
    @PostMapping("/insert")
    public ApiResponse<CamelCaseMap> insertProject(@RequestBody Project project) {
        int insertedCount = projectService.insertProject(project);
        return ApiResponse.ok(null);
    }

    /**
     * 프로젝트 상세 조회
     *
     * @return
     */
    @GetMapping("/{projectId}")
    @ResponseBody
    public ApiResponse<CamelCaseMap> findProjectDetail(@PathVariable("projectId") int projectId) {
        CamelCaseMap data = projectService.findProjectDetail(projectId);

        if (data == null) {
            throw new CustomException(ErrorCode.PROJECT_NOT_FOUND);
        }
        return ApiResponse.ok(data);
    }

    /**
     * 프로젝트 지원
     *
     * @return
     */
    @PostMapping("/{projectId}/apply")
    public ApiResponse<CamelCaseMap> applyToProject(
            @PathVariable("projectId") int projectId,
            @RequestBody Map<String, Object> body
    ) {
        int userId = (int) body.get("userId");
        int postId = (int) body.get("postId");
        boolean success = projectService.applyProject(projectId, userId, postId);
        if (success) {
            return ApiResponse.ok(null);
        } else {
            throw new CustomException(ErrorCode.BAD_REQUEST);
        }
    }

    /**
     * My Project 프로젝트 조회
     *
     * @param projectSearchCriteria
     * @return
     */
    @GetMapping
    public ApiResponse<Map<String ,Object>> findMyProjectList(ProjectSearchCriteria projectSearchCriteria) {
        Map<String, Object> result = new HashMap<>();
        // 생성한 프로젝트 조회
        List<CamelCaseMap> createProjectList = projectService.findCreateProjectList(projectSearchCriteria);
        if(ObjectUtils.isEmpty(createProjectList)) {
            createProjectList = new ArrayList<>();
        }

        // 참여한 프로젝트 조회
        List<CamelCaseMap> participationProjectList = projectService.findParticipationProjectList(projectSearchCriteria);
        if(ObjectUtils.isEmpty(participationProjectList)) {
            participationProjectList = new ArrayList<>();
        }
        result.put("createdProject", createProjectList);
        result.put("participationProject", participationProjectList);

        return ApiResponse.ok(result);
    }

    /**
     * 프로젝트 지원 유저 목록 조회
     *
     * @return
     */
    @GetMapping("/{projectId}/applyMembers")
    public ApiResponse<List<ProjectUserResponse>> findProjectApplyMemberList(@PathVariable int projectId) {
        List<ProjectUserResponse> applicantList = projectService.findProjectApplyMemberList(projectId);
        if (ObjectUtils.isEmpty(applicantList)) {
            applicantList = new ArrayList<>();
        }
        return ApiResponse.ok(applicantList);
    }

    /**
     * 프로젝트 참여 인원 조회
     *
     * @param projectId
     * @return
     */
    @GetMapping("/{projectId}/members")
    public ApiResponse<List<CamelCaseMap>> findProjectMemberList(@PathVariable("projectId") int projectId) {
        List<CamelCaseMap> data = projectService.findProjectMemberList(projectId);
        if(ObjectUtils.isEmpty(data)) {
            throw new CustomException(ErrorCode.MEMBER_NOT_FOUND);
        }

        return ApiResponse.ok(data);
    }
}
