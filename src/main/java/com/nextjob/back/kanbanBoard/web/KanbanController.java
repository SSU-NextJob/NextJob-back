package com.nextjob.back.kanbanBoard.web;

import com.nextjob.back.kanbanBoard.service.KanbanService;
import com.nextjob.base.exception.CustomException;
import com.nextjob.base.exception.ErrorCode;
import com.nextjob.base.util.CamelCaseMap;
import com.nextjob.base.web.response.ApiResponse;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/kanban")
public class KanbanController {

    private final KanbanService kanbanService;

    public KanbanController(KanbanService kanbanService) {
        this.kanbanService = kanbanService;
    }

    /**
     * 작업 목록 조회
     *
     * @param body
     * @return
     */
    @GetMapping("/tasks")
    public ApiResponse<List<CamelCaseMap>> findTaskList(@RequestBody Map<String, Object> body) {
        int kanbanId = Integer.parseInt(body.get("kanbanId").toString());
        int taskId = Integer.parseInt(body.get("taskId").toString());

        List<CamelCaseMap> taskList = kanbanService.findTaskList(kanbanId, taskId);
        if(ObjectUtils.isEmpty(taskList)) {
            throw new CustomException(ErrorCode.TASK_NOT_FOUND);
        }
        return ApiResponse.ok(taskList);
    }

    /**
     * 작업 생성
     *
     * @return
     */
    @PostMapping("/tasks/insert")
    public ApiResponse<Map<String, Object>> insertTask(@RequestBody KanbanSearchCriteria kanbanSearchCriteria) {
        Map<String, Object> result = new HashMap<>();
        result = kanbanService.insertTask(kanbanSearchCriteria);
        return ApiResponse.ok(result);
    }

    /**
     * 작업 수정
     *
     * @return
     */
    @PutMapping("/tasks/{taskId}")
    public ApiResponse<Map<String, Object>> updateTask(@PathVariable("taskId") int taskId, @RequestBody KanbanSearchCriteria kanbanSearchCriteria) {
        Map<String, Object> result = new HashMap<>();
        kanbanSearchCriteria.setTaskId(taskId);
        result = kanbanService.updateTask(kanbanSearchCriteria);
        return ApiResponse.ok(result);
    }

    /**
     * 작업 제거
     *
     * @return
     */
    @DeleteMapping("/tasks/{taskId}")
    public ApiResponse<Map<String, Object>> deleteTask(@PathVariable("taskId") int taskId) {
        Map<String, Object> result = new HashMap<>();
        result = kanbanService.deleteTask(taskId);
        return ApiResponse.ok(result);
    }

}
