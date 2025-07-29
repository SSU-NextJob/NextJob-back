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
    private String status;

    /* 프로젝트 이미지 */
    private String image;

    /* 프로젝트 종류 */
    private String type;

    /* 프로젝트 생성자 */
    private String userId;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
    }

    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
