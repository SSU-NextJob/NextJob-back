package com.nextjob.back.request.service;

import com.nextjob.base.util.CamelCaseMap;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RequestMapper {

    /* 요청 상세 조회 */
    CamelCaseMap findRequestDetail(@Param("requestId") int requestId);

    /* 사용자에게 온 요청 목록 조회 (프로젝트 지원) */
    List<CamelCaseMap> findIncomingApplyList(
            @Param("userId") int userId,
            @Param("requestType") String requestType,
            @Param("jobTitle") String jobTitle
    );

    /* 사용자에게 온 요청 목록 조회 (협업 제안) */
    List<CamelCaseMap> findIncomingSuggestList(
            @Param("userId") int userId,
            @Param("requestType") String requestType
    );

    /* 사용자가 보낸 요청 목록 조회 (프로젝트 지원) */
    List<CamelCaseMap> findOutgoingApplyList(
            @Param("userId") int userId,
            @Param("requestType") String requestType
    );

    /* 사용자가 보낸 요청 목록 조회 (협업 제안) */
    List<CamelCaseMap> findOutgoingSuggestList(
            @Param("userId") int userId,
            @Param("requestType") String requestType,
            @Param("jobTitle") String jobTitle
    );

    /* 요청 상태 변경 */
    void updateRequestStatus(@Param("requestId") int requestId, @Param("status") String status);
}