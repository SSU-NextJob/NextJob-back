package com.nextjob.back.project.web;

import com.nextjob.back.project.domain.Project;
import com.nextjob.back.project.service.ProjectService;
import com.nextjob.back.user.service.UserService;
import com.nextjob.base.util.CamelCaseMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/projects")
public class ProjectController {

    private ProjectService projectService;

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
    public Map<String, Object> insertProject(@RequestBody Project project) {
        int insertedCount = projectService.insertProject(project);
        Map<String, Object> result = new HashMap<>();
        result.put("success", insertedCount > 0);
        return result;
    }

    /**
     * 프로젝트 상세 조회
     *
     * @return
     */
    @GetMapping("/{id}")
    @ResponseBody
    public Map<String, Object> findProjectDetail(@PathVariable("id") int projectId) {
        Map<String, Object> result = new HashMap<>();
        CamelCaseMap data = projectService.findProjectDetail(projectId);

        result.put("success", data != null);
        result.put("data", data);

        return result;
    }
}
