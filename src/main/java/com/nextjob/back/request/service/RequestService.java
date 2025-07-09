package com.nextjob.back.request.service;

import com.nextjob.base.util.CamelCaseMap;

import java.util.List;

public interface RequestService {

    /* 사용자에게 온 요청 목록 조회 (프로젝트 지원) */
    List<CamelCaseMap> findIncomingApplyList(int userId);

    /* 사용자에게 온 요청 목록 조회 (협업 제안) */
    List<CamelCaseMap> findIncomingSuggestList(int userId);

    /* 사용자가 보낸 요청 목록 조회 (프로젝트 지원) */
    List<CamelCaseMap> findOutgoingApplyList(int userId);

    /* 사용자가 보낸 요청 목록 조회 (협업 제안) */
    List<CamelCaseMap> findOutgoingSuggestList(int userId);
}
