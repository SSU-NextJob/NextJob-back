package com.nextjob.back.schedule.web;

import com.nextjob.base.domain.Domain;
import com.nextjob.base.web.servlet.search.Search;

public class ScheduleSearchCriteria extends Domain implements Search {

    /* 일정 아이디 */
    private int scheduleId;

    /* 워크스페이스 아이디 */
    private int workspaceId;

    /* 제목 */
    private String title;

    /* 내용 */
    private String content;

    /* 시작일 */
    private String startDate;

    /* 종료일 */
    private String endDate;

    /* 담당자 */
    private int[] assignees;

    /* 생성자 */
    private int userId;

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public int getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(int workspaceId) {
        this.workspaceId = workspaceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int[] getAssignees() {
        return assignees;
    }

    public void setAssignees(int[] assignees) {
        this.assignees = assignees;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
