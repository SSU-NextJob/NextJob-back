package com.nextjob.back.project.web;

import com.nextjob.back.project.domain.Project;
import com.nextjob.back.project.service.ProjectService;
import com.nextjob.base.exception.CustomException;
import com.nextjob.base.exception.ErrorCode;
import com.nextjob.base.util.CamelCaseMap;
import com.nextjob.base.web.response.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
        Map<String, Object> result = new HashMap<>();
        CamelCaseMap data = projectService.findProjectDetail(projectId);

        result.put("success", data != null);
        result.put("data", data);

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
        boolean success = projectService.applyProject(projectId, userId);
        if (success) {
            return ApiResponse.ok(null);
        } else {
            throw new CustomException(ErrorCode.BAD_REQUEST);
        }
    }
}
