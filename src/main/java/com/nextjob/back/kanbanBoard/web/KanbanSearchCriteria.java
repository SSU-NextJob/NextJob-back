package com.nextjob.back.kanbanBoard.web;

import com.nextjob.back.kanbanBoard.domain.TaskUsers;
import com.nextjob.base.domain.Domain;
import com.nextjob.base.web.servlet.search.Search;

import java.util.Date;
import java.util.List;

public class KanbanSearchCriteria extends Domain implements Search {

    /* 칸반아이디 */
    private int kanbanId;

    /* 컬럼아이디 */
    private int columnId;

    /* 업무아이디 */
    private int taskId;

    /* 제목 */
    private String subject;

    /* 본문 */
    private String content;

    /* 시작일 */
    private Date startDate;

    /* 종료일 */
    private Date endDate;

    /* 정렬순서 */
    private int sort;

    /* 중요도 */
    private String importance;

    private List<TaskUsers> users;

    public int getKanbanId() {
        return kanbanId;
    }

    public void setKanbanId(int kanbanId) {
        this.kanbanId = kanbanId;
    }

    public int getColumnId() {
        return columnId;
    }

    public void setColumnId(int columnId) {
        this.columnId = columnId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getImportance() {
        return importance;
    }

    public void setImportance(String importance) {
        this.importance = importance;
    }

    public List<TaskUsers> getUsers() {
        return users;
    }

    public void setUsers(List<TaskUsers> users) {
        this.users = users;
    }
}
