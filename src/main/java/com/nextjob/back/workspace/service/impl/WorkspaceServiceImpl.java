package com.nextjob.back.workspace.service.impl;

import com.nextjob.back.workspace.service.WorkspaceMapper;
import com.nextjob.back.workspace.service.WorkspaceService;
import com.nextjob.back.workspace.web.WorkspaceDetailResponse;
import org.springframework.stereotype.Service;

@Service
public class WorkspaceServiceImpl implements WorkspaceService {

    private final WorkspaceMapper workspaceMapper;

    public WorkspaceServiceImpl(WorkspaceMapper workspaceMapper) {
        this.workspaceMapper = workspaceMapper;
    }


    /**
    * 워크스페이스 상세 조회
    *
    * @param
    * @return
    */
    @Override
    public WorkspaceDetailResponse findWorkspaceDetail(int workspaceId) {
        return workspaceMapper.findWorkspaceDetail(workspaceId);
    }
}

