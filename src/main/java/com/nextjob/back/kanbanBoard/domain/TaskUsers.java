package com.nextjob.back.kanbanBoard.domain;

import com.nextjob.base.domain.Domain;

public class TaskUsers extends Domain {

    private int userId;

    private int taskId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
}
