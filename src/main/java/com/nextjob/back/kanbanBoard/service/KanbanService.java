package com.nextjob.back.kanbanBoard.service;

import com.nextjob.back.kanbanBoard.web.KanbanSearchCriteria;
import com.nextjob.base.util.CamelCaseMap;

import java.util.List;
import java.util.Map;

public interface KanbanService {

    List<CamelCaseMap> findTaskList(int kanbanId);

    Map<String, Object> insertTask(KanbanSearchCriteria kanbanSearchCriteria);

    Map<String, Object> updateTask(KanbanSearchCriteria kanbanSearchCriteria);

    Map<String, Object> deleteTask(KanbanSearchCriteria kanbanSearchCriteria);

    Map<String, Object> findTaskDetail(int kanbanId, int taskId);

    List<CamelCaseMap> findColumnList(int kanbanId);
}
