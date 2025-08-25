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
     * @return
     */
    @Override
    public List<CamelCaseMap> findTaskList(int kanbanId) {
        return kanbanBoardMapper.findTaskList(kanbanId);
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
        kanbanTasks.setUserId(kanbanSearchCriteria.getUserId());

        int taskInsertedCount = kanbanBoardMapper.insertTask(kanbanTasks);

        for (int i = 0; i < kanbanSearchCriteria.getUser().length; i++) {
            TaskUsers user = new TaskUsers();
            user.setUserId(kanbanSearchCriteria.getUser()[i]);
            user.setTaskId(kanbanTasks.getTaskId());
            user.setKanbanId(kanbanSearchCriteria.getKanbanId());
            int userInsertedCount = kanbanBoardMapper.insertTaskUsers(user);
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

        int userDeletedCount = kanbanBoardMapper.deleteTaskUsers(kanbanTasks.getTaskId(), kanbanTasks.getKanbanId());

        for (int i = 0; i < kanbanSearchCriteria.getUser().length; i++) {
            TaskUsers user = new TaskUsers();
            user.setUserId(kanbanSearchCriteria.getUser()[i]);
            user.setTaskId(kanbanTasks.getTaskId());
            user.setKanbanId(kanbanTasks.getKanbanId());
            int userInsertedCount = kanbanBoardMapper.insertTaskUsers(user);
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
    public Map<String, Object> deleteTask(KanbanSearchCriteria kanbanSearchCriteria) {
        Map<String, Object> result = new HashMap<>();

        int taskId = kanbanSearchCriteria.getTaskId();
        int kanbanId = kanbanSearchCriteria.getKanbanId();
        int taskDeletedCount = kanbanBoardMapper.deleteTask(taskId, kanbanId);
        int userDeletedCount = kanbanBoardMapper.deleteTaskUsers(taskId, kanbanId);

        if (taskDeletedCount > 0) {
            result.put("success", true);
            result.put("message", "저장되었습니다.");
        } else {
            throw new CustomException(ErrorCode.DELETE_ERROR);
        }

        return result;
    }

    /**
     * 칸반 작업 상세 조회
     *
     * @param taskId
     * @return
     */
    @Override
    public Map<String, Object> findTaskDetail(int kanbanId, int taskId) {
        Map<String, Object> result = kanbanBoardMapper.findTaskDetail(kanbanId, taskId);
        if(!result.isEmpty()) {
            result.put("users", kanbanBoardMapper.findTaskUserList(kanbanId, taskId));
        }
        return result;
    }

    @Override
    public List<CamelCaseMap> findColumnList(int kanbanId) {
        return kanbanBoardMapper.findColumnList(kanbanId);
    }

}
