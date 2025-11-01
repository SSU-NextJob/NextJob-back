package com.nextjob.back.schedule.domain;

import com.nextjob.base.domain.Domain;

public class ScheduleUser extends Domain {

    /* 유저 아이디 */
    private int userId;

    /* 일정 아이디 */
    private int scheduleId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }
}
