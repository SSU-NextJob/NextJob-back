package com.nextjob.back.workspace.web;

import com.nextjob.back.drive.web.DriveDetailResponse;
import com.nextjob.back.kanbanBoard.web.KanbanDetailResponse;
import com.nextjob.base.web.servlet.search.Search;

@SuppressWarnings("serial")
public class WorkspaceDetailResponse implements Search {
    private int workspaceId;
    private int projectId;
    private KanbanDetailResponse kanban;
    private DriveDetailResponse drive;

    public int getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(int workspaceId) {
        this.workspaceId = workspaceId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public KanbanDetailResponse getKanban() {
        return kanban;
    }

    public void setKanban(KanbanDetailResponse kanban) {
        this.kanban = kanban;
    }

    public DriveDetailResponse getDrive() {
        return drive;
    }

    public void setDrive(DriveDetailResponse drive) {
        this.drive = drive;
    }
}
