package com.nextjob.back.workspace.service;

import com.nextjob.back.workspace.web.WorkspaceDetailResponse;
import com.nextjob.base.util.CamelCaseMap;

import java.util.List;

public interface WorkspaceService {

    /* 워크스페이스 상세 조회 */
    WorkspaceDetailResponse findWorkspaceDetail(int workspaceId);

    /* 워크스페이스 속한 팀원 조회 */ 
    List<CamelCaseMap> findWorkspaceUsers(int workspaceId);
}
