package com.nextjob.back.workspace.domain;

import com.nextjob.base.domain.Domain;

public class Workspace extends Domain {

    /* 워크스페이스ID */
    private int workspaceId;

    /* 프로젝트ID */
    private int projectId;

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
}
