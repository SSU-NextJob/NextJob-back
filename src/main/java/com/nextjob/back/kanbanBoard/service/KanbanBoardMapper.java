package com.nextjob.back.kanbanBoard.service;

import com.nextjob.back.kanbanBoard.domain.KanbanBoard;
import org.apache.ibatis.annotations.Mapper;
import com.nextjob.back.kanbanBoard.domain.KanbanTasks;
import com.nextjob.back.kanbanBoard.domain.TaskUsers;
import com.nextjob.base.util.CamelCaseMap;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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

    /* 칸반 작업 상세 조회 */
    Map<String, Object> findTaskDetail(@Param("kanbanId") int kanbanId, @Param("taskId") int taskId);

    /* 칸반 작업 담당자 목록 조회 */
    List<CamelCaseMap> findTaskUserList(@Param("kanbanId") int kanbanId, @Param("taskId") int taskId);

    /* 컬럼 목록 조회 */
    List<CamelCaseMap> findColumnList(@Param("kanbanId") int kanbanId);

    /* 작업 상태 변경 */
    int updateTaskStatus(KanbanTasks task);
}