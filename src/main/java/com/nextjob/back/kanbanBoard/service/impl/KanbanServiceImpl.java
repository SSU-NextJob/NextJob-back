package com.nextjob.back.kanbanBoard.service.impl;

import com.nextjob.back.kanbanBoard.domain.KanbanTasks;
import com.nextjob.back.kanbanBoard.domain.TaskUsers;
import com.nextjob.back.kanbanBoard.service.KanbanBoardMapper;
import com.nextjob.back.kanbanBoard.service.KanbanService;
import com.nextjob.back.kanbanBoard.web.KanbanSearchCriteria;
import com.nextjob.base.exception.CustomException;
import com.nextjob.base.exception.ErrorCode;
import com.nextjob.base.util.CamelCaseMap;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class KanbanServiceImpl implements KanbanService {

    private final KanbanBoardMapper kanbanBoardMapper;

    public KanbanServiceImpl (KanbanBoardMapper kanbanBoardMapper) {
        this.kanbanBoardMapper = kanbanBoardMapper;
    }

    /**
     * 칸반 작업 목록 조회
     *
     * @param kanbanId
     * @param taskId
     * @return
     */
    @Override
    public List<CamelCaseMap> findTaskList(int kanbanId, int taskId) {
        return kanbanBoardMapper.findTaskList(kanbanId, taskId);
    }

    @Override
    public Map<String, Object> insertTask(KanbanSearchCriteria kanbanSearchCriteria) {
        Map<String, Object> result = new HashMap<>();

        KanbanTasks kanbanTasks = new KanbanTasks();
        kanbanTasks.setKanbanId(kanbanSearchCriteria.getKanbanId());
        kanbanTasks.setColumnId(kanbanSearchCriteria.getColumnId());
        kanbanTasks.setSubject(kanbanSearchCriteria.getSubject());
        kanbanTasks.setContent(kanbanSearchCriteria.getContent());
        kanbanTasks.setStartDate(kanbanSearchCriteria.getStartDate());
        kanbanTasks.setEndDate(kanbanSearchCriteria.getEndDate());
        kanbanTasks.setSort(kanbanSearchCriteria.getSort());
        kanbanTasks.setImportance(kanbanSearchCriteria.getImportance());

        int taskInsertedCount = kanbanBoardMapper.insertTask(kanbanTasks);

        List<TaskUsers> taskUserList = kanbanSearchCriteria.getUsers();
        for (TaskUsers taskUser : taskUserList) {
            int userInsertedCount = kanbanBoardMapper.insertTaskUsers(taskUser);
            if(userInsertedCount == 0) {
                throw new CustomException(ErrorCode.INSERT_ERROR);
            }
        }

        if (taskInsertedCount > 0) {
            result.put("success", true);
            result.put("message", "저장되었습니다.");
        } else {
            throw new CustomException(ErrorCode.INSERT_ERROR);
        }

        return result;
    }

    @Override
    public Map<String, Object> updateTask(KanbanSearchCriteria kanbanSearchCriteria) {
        Map<String, Object> result = new HashMap<>();

        KanbanTasks kanbanTasks = new KanbanTasks();
        kanbanTasks.setTaskId(kanbanSearchCriteria.getTaskId());
        kanbanTasks.setKanbanId(kanbanSearchCriteria.getKanbanId());
        kanbanTasks.setColumnId(kanbanSearchCriteria.getColumnId());
        kanbanTasks.setSubject(kanbanSearchCriteria.getSubject());
        kanbanTasks.setContent(kanbanSearchCriteria.getContent());
        kanbanTasks.setStartDate(kanbanSearchCriteria.getStartDate());
        kanbanTasks.setEndDate(kanbanSearchCriteria.getEndDate());
        kanbanTasks.setSort(kanbanSearchCriteria.getSort());
        kanbanTasks.setImportance(kanbanSearchCriteria.getImportance());

        int taskUpdatedCount = kanbanBoardMapper.updateTask(kanbanTasks);

        int userDeletedCount = kanbanBoardMapper.deleteTaskUsers(kanbanTasks.getTaskId());

        List<TaskUsers> taskUserList = kanbanSearchCriteria.getUsers();
        for (TaskUsers taskUser : taskUserList) {
            int userInsertedCount = kanbanBoardMapper.insertTaskUsers(taskUser);
            if(userInsertedCount == 0) {
                throw new CustomException(ErrorCode.INSERT_ERROR);
            }
        }

        if (taskUpdatedCount > 0) {
            result.put("success", true);
            result.put("message", "저장되었습니다.");
        } else {
            throw new CustomException(ErrorCode.UPDATE_ERROR);
        }

        return result;
    }

    @Override
    public Map<String, Object> deleteTask(int taskId) {
        int taskDeletedCount = kanbanBoardMapper.deleteTask(taskId);
        int userDeletedCount = kanbanBoardMapper.deleteTaskUsers(taskId);
        return null;
    }
}
