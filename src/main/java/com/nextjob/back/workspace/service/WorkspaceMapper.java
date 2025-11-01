package com.nextjob.back.workspace.service;

import com.nextjob.back.workspace.domain.Workspace;
import com.nextjob.back.workspace.web.WorkspaceDetailResponse;
import com.nextjob.base.util.CamelCaseMap;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WorkspaceMapper {

    /* 워크스페이스 상세 조회 */
    WorkspaceDetailResponse findWorkspaceDetail(@Param("workspaceId") int workspaceId);

    /* 워크스페이스 생성 */
    int insertWorkSpace(Workspace workspace);

    /* 워크스페이스 팀원 목록 조회 */ 
    List<CamelCaseMap> findWorkspaceUsers(int workspaceId);
}