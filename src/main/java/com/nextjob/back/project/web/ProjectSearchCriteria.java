package com.nextjob.back.project.web;

import com.nextjob.base.domain.Domain;
import com.nextjob.base.web.servlet.search.Search;

import java.util.Date;

@SuppressWarnings("serial")
public class ProjectSearchCriteria extends Domain implements Search {
    /* 프로젝트 ID */
    private int projectId;

    /* 프로젝트 이름 */
    private String name;

    /* 프로젝트 내용 */
    private String content;

    /* 진행 시작일 */
    private Date startAt;

    /* 진행 종료일 */
    private Date endAt;

    /* 프로젝트 진행 상태 */
    private String projectType;

    /* 프로젝트 이미지 */
    private String image;

    /* 프로젝트 종류 */
    private String type;
}
