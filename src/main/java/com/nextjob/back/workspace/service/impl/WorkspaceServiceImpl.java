package com.nextjob.back.workspace.service.impl;

import com.nextjob.back.workspace.service.WorkspaceMapper;
import com.nextjob.back.workspace.service.WorkspaceService;
import com.nextjob.back.workspace.web.WorkspaceDetailResponse;
import com.nextjob.base.util.CamelCaseMap;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /**
     * 워크스페이스 팀원 목록 조회
     *
     * @param
     * @return
     */
    @Override
    public List<CamelCaseMap> findWorkspaceUsers(int workspaceId) {
        return workspaceMapper.findWorkspaceUsers(workspaceId);
    }
}

