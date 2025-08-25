package com.nextjob.back.kanbanBoard.service;

import com.nextjob.back.kanbanBoard.domain.KanbanBoard;
import com.nextjob.back.kanbanBoard.domain.KanbanTasks;
import com.nextjob.back.kanbanBoard.domain.TaskUsers;
import com.nextjob.base.util.CamelCaseMap;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface KanbanBoardMapper {
    /* 칸반 생성 */
    int insertKanbanBoard(KanbanBoard kanbanBoard);

    /* 칸반 기본 컬럼 생성 */
    int insertKanbanDefaultColumn(int kanbanId);

    /* 칸반 작업 목록 조회 */
    List<CamelCaseMap> findTaskList(@Param("kanbanId") int kanbanId);

    int insertTask(KanbanTasks kanbanTasks);

    int insertTaskUsers(TaskUsers taskUsers);

    int updateTask(KanbanTasks kanbanTasks);

    int deleteTaskUsers(@Param("taskId") int taskId, @Param("kanbanId") int kanbanId);

    int deleteTask(@Param("taskId") int taskId, @Param("kanbanId") int kanbanId);

    /* 컬럼 목록 조회 */
    List<CamelCaseMap> findColumnList(int kanbanId);
}