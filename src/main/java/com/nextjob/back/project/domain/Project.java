package com.nextjob.back.project.domain;

import com.nextjob.base.domain.Domain;
import lombok.Getter;

import java.util.Date;

public class Project extends Domain {
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

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }

    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
