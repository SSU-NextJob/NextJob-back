package com.nextjob.back.request.service.impl;

import com.nextjob.back.request.service.RequestMapper;
import com.nextjob.back.request.service.RequestService;
import com.nextjob.base.util.CamelCaseMap;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {

    private final RequestMapper requestMapper;

    public RequestServiceImpl(RequestMapper requestMapper) {
        this.requestMapper = requestMapper;
    }

    /* 요청 상세 조회 */
    @Override
    public CamelCaseMap findRequestDetail(int requestId) {
        return requestMapper.findRequestDetail(requestId);
    }

    /* 사용자에게 온 요청 목록 조회 (프로젝트 지원) */
    @Override
    public List<CamelCaseMap> findIncomingApplyList(int userId) {
        String requestType = "APPLY";
        String jobTitle = "LEADER";

        return requestMapper.findIncomingApplyList(userId, requestType, jobTitle);
    }

    /* 사용자에게 온 요청 목록 조회 (협업 제안) */
    @Override
    public List<CamelCaseMap> findIncomingSuggestList(int userId) {
        String requestType = "SUGGEST";

        return requestMapper.findIncomingSuggestList(userId, requestType);
    }

    /* 사용자가 보낸 요청 목록 조회 (프로젝트 지원) */
    @Override
    public List<CamelCaseMap> findOutgoingApplyList(int userId) {
        String requestType = "APPLY";

        return requestMapper.findOutgoingApplyList(userId, requestType);
    }

    /* 사용자가 보낸 요청 목록 조회 (협업 제안) */
    @Override
    public List<CamelCaseMap> findOutgoingSuggestList(int userId) {
        String requestType = "SUGGEST";
        String jobTitle = "LEADER";

        return requestMapper.findOutgoingSuggestList(userId, requestType, jobTitle);
    }

    /* 요청 상태 변경 */
    @Override
    public void updateRequestStatus(int requestId, String status) {
        requestMapper.updateRequestStatus(requestId, status);
    }
}

