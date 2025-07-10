package com.nextjob.back.request.web;

import com.nextjob.back.project.service.ProjectService;
import com.nextjob.back.request.service.RequestService;
import com.nextjob.back.user.service.UserService;
import com.nextjob.base.exception.CustomException;
import com.nextjob.base.exception.ErrorCode;
import com.nextjob.base.util.CamelCaseMap;
import com.nextjob.base.web.response.ApiResponse;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/requests")
public class RequestController {
    private final RequestService requestService;
    private final UserService userService;
    private final ProjectService projectService;

    public RequestController(
            RequestService requestService,
            UserService userService,
            ProjectService projectService
    ) {
        this.requestService = requestService;
        this.userService = userService;
        this.projectService = projectService;
    }

    // 요청 목록 조회
    @GetMapping
    public ApiResponse<Map<String, Object>> findUserList(@RequestParam int userId) {
        CamelCaseMap user = userService.findUserDetail(userId);
        if (ObjectUtils.isEmpty(user)) {
            throw new CustomException(ErrorCode.NOT_FOUND);
        }

        // 사용자에게 온 요청 목록
        List<CamelCaseMap> incomingApply = requestService.findIncomingApplyList(userId);
        List<CamelCaseMap> incomingSuggest = requestService.findIncomingSuggestList(userId);

        // 사용자가 보낸 요청 목록
        List<CamelCaseMap> outgoingApply = requestService.findOutgoingApplyList(userId);
        List<CamelCaseMap> outgoingSuggest = requestService.findOutgoingSuggestList(userId);

        // 응답 구조 설계
        Map<String, Object> incomingRequests = new HashMap<>();
        incomingRequests.put("apply", incomingApply);
        incomingRequests.put("suggest", incomingSuggest);

        Map<String, Object> outgoingRequests = new HashMap<>();
        outgoingRequests.put("apply", outgoingApply);
        outgoingRequests.put("suggest", outgoingSuggest);

        Map<String, Object> data = new HashMap<>();
        data.put("incomingRequests", incomingRequests);
        data.put("outgoingRequests", outgoingRequests);

        return ApiResponse.ok(data);
    }

    // 요청 수락/거절
    @PostMapping("/{requestId}/reply")
    public ApiResponse<CamelCaseMap> replyToRequest(
            @PathVariable("requestId") int requestId,
            @RequestBody Map<String, Object> body
    ) {
        CamelCaseMap request = requestService.findRequestDetail(requestId);
        if (ObjectUtils.isEmpty(request)) {
            throw new CustomException(ErrorCode.NOT_FOUND);
        }

        // ACCEPT(수락), REJECT(거절) 만 허용
        String status = (String) body.get("status");
        if (status == null || status.isBlank() || (!status.equals("ACCEPTED") && !status.equals("REJECTED"))) {
            throw new CustomException(ErrorCode.BAD_REQUEST);
        }

        requestId = request.getInt("requestId");

        // 요청 상태 변경
        requestService.updateRequestStatus(requestId, status);

        request = requestService.findRequestDetail(requestId);
        status = request.getString("requestStatus");

        // 요청 수락 시 프로젝트 멤버로 등록
        if (status.equals("ACCEPTED")) {
            int projectId = request.getInt("projectId");
            int userId = request.getInt("userId");
            String requestType = request.getString("requestType");
            String jobTitle = "MEMBER";

            if (requestType.equals("APPLY")) {
                projectService.insertProjectMember(projectId, userId, jobTitle);
            } else if (requestType.equals("SUGGEST")) {
                projectService.insertProjectMember(projectId, userId, jobTitle);
            }
        }

        return ApiResponse.ok(null);
    }
}
