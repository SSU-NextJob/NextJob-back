package com.nextjob.back.request.web;

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

    public RequestController(
            RequestService requestService,
            UserService userService
    ) {
        this.requestService = requestService;
        this.userService = userService;
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
}
