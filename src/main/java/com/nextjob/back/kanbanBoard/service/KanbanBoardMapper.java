package com.nextjob.back.kanbanBoard.service;

import com.nextjob.back.kanbanBoard.domain.KanbanBoard;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface KanbanBoardMapper {
    /* 칸반 생성 */
    int insertKanbanBoard(KanbanBoard kanbanBoard);

    /* 칸반 기본 컬럼 생성 */
    int insertKanbanDefaultColumn(int kanbanId);
}