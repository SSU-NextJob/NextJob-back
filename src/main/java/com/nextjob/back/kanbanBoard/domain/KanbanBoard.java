package com.nextjob.back.kanbanBoard.domain;

import com.nextjob.base.domain.Domain;

public class KanbanBoard extends Domain {
    /* 칸반보드 아이디 */
    private int kanbanId;

    /* 워크스페이스 아이디 */
    private int workspaceId;

    public int getKanbanId() {
        return kanbanId;
    }

    public void setKanbanId(int kanbanId) {
        this.kanbanId = kanbanId;
    }

    public int getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(int workspaceId) {
        this.workspaceId = workspaceId;
    }

}
