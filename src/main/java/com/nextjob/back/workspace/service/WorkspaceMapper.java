package com.nextjob.back.workspace.service;

import com.nextjob.back.workspace.web.WorkspaceDetailResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WorkspaceMapper {

    /* 워크스페이스 상세 조회 */
    WorkspaceDetailResponse findWorkspaceDetail(@Param("workspaceId") int workspaceId);
}