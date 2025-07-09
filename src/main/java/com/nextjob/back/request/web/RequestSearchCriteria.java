package com.nextjob.back.request.web;

import com.nextjob.base.domain.Domain;
import com.nextjob.base.web.servlet.search.Search;

import java.util.Date;

@SuppressWarnings("serial")
public class RequestSearchCriteria extends Domain implements Search {
    /* 요청 ID */
    private int requestId;

    /* 프로젝트 ID */
    private String projectId;

    /* 사용자 ID */
    private String userId;

    /* 요청 상태 */
    private String requestStatus;

    /* 요청 종류 */
    private String requestType;

    /* 요청 생성 일자 */
    private Date requestDate;

}
