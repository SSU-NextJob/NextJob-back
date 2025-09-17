package com.nextjob.back.kanbanBoard.web;

import com.nextjob.back.kanbanBoard.domain.KanbanTasks;
import com.nextjob.back.kanbanBoard.service.KanbanService;
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

        List<CamelCaseMap> taskList = kanbanService.findTaskList(kanbanId);
        if(ObjectUtils.isEmpty(taskList)) {
            taskList = new ArrayList<>();
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
    public ApiResponse<Map<String, Object>> deleteTask(@PathVariable("taskId") int taskId, @RequestBody KanbanSearchCriteria kanbanSearchCriteria) {
        Map<String, Object> result = new HashMap<>();
        kanbanSearchCriteria.setTaskId(taskId);
        result = kanbanService.deleteTask(kanbanSearchCriteria);
        return ApiResponse.ok(result);
    }

    /**
     * 작업 상세 조회
     *
     * @return
     */
    @GetMapping("/tasks/{taskId}")
    public ApiResponse<Map<String, Object>> findTaskDetail(@PathVariable("taskId") int taskId, @RequestBody Map<String, Object> body) {
        int kanbanId = Integer.parseInt(body.get("kanbanId").toString());
        Map<String, Object> result = kanbanService.findTaskDetail(kanbanId, taskId);
        if (result == null) {
            throw new CustomException(ErrorCode.TASK_NOT_FOUND);
        }
        return ApiResponse.ok(result);
    }

    /**
     * 컬럼 목록 조회
     *
     * @param body
     * @return
     */
    @GetMapping("/columns")
    public ApiResponse<List<CamelCaseMap>> findColumnList(@RequestBody Map<String, Object> body) {
        int kanbanId = Integer.parseInt(body.get("kanbanId").toString());

        List<CamelCaseMap> columnList = kanbanService.findColumnList(kanbanId);
        if(ObjectUtils.isEmpty(columnList)) {
            columnList = new ArrayList<>();
        }
        return ApiResponse.ok(columnList);
    }

    /**
     * 작업 상태 변경
     *
     * @param body
     * @return
     */
    @PatchMapping("/tasks/status")
    public ApiResponse<List<CamelCaseMap>> updateTaskStatus(@RequestBody Map<String, Object> body) {
        List<KanbanTasks> tasksToUpdate = new ArrayList<>();
        int kanbanId = Integer.parseInt(body.get("kanbanId").toString());

        List<Map<String, Object>> tasksList = (List<Map<String, Object>>) body.get("tasks");

        for (Map<String, Object> columnData : tasksList) {
            int columnId = Integer.parseInt(columnData.get("columnId").toString());
            List<Map<String, Object>> taskItems = (List<Map<String, Object>>) columnData.get("items");

            // KanbanTasks 객체 생성하여 업데이트 리스트에 추가
            for (Map<String, Object> taskMap : taskItems) {
                KanbanTasks task = new KanbanTasks();
                task.setKanbanId(kanbanId);
                task.setColumnId(columnId);
                task.setTaskId(Integer.parseInt(taskMap.get("taskId").toString()));
                task.setSort(Integer.parseInt(taskMap.get("sort").toString()));

                tasksToUpdate.add(task);
            }
        }

        if (!tasksToUpdate.isEmpty()) {
            int count = kanbanService.updateTaskStatus(tasksToUpdate);
        }

        return ApiResponse.ok(null);
    }
}
