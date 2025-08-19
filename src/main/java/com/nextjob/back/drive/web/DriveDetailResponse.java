package com.nextjob.back.drive.web;

import com.nextjob.base.web.servlet.search.Search;

@SuppressWarnings("serial")
public class DriveDetailResponse implements Search {
    private int driveId;

    public int getDriveId() {
        return driveId;
    }

    public void setDriveId(int driveId) {
        this.driveId = driveId;
    }
}
