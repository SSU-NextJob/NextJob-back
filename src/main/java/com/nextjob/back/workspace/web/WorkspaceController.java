package com.nextjob.back.workspace.web;

import com.nextjob.back.workspace.service.WorkspaceService;
import com.nextjob.base.exception.CustomException;
import com.nextjob.base.exception.ErrorCode;
import com.nextjob.base.web.response.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/workspaces")
public class WorkspaceController {

    private final WorkspaceService workspaceService;

    public WorkspaceController(WorkspaceService workspaceService) {
        this.workspaceService = workspaceService;
    }

    /**
     * 워크스페이스 상세 조회
     *
     * @param workspaceId 워크스페이스 ID
     * @return 워크스페이스와 연결되어있는 kanban, drive 정보 전달
     */
    @GetMapping("/{workspaceId}")
    public ApiResponse<WorkspaceDetailResponse> findWorkspaceDetail(@PathVariable("workspaceId") int workspaceId) {
        WorkspaceDetailResponse workspace = workspaceService.findWorkspaceDetail(workspaceId);
        if (workspace == null) {
            throw new CustomException(ErrorCode.NOT_FOUND);
        }
        return ApiResponse.ok(workspace);
    }
}
