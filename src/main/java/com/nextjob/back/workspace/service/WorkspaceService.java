package com.nextjob.back.workspace.service;

import com.nextjob.back.workspace.web.WorkspaceDetailResponse;

public interface WorkspaceService {

    /* 워크스페이스 상세 조회 */
    WorkspaceDetailResponse findWorkspaceDetail(int workspaceId);
}
