package com.nextjob.back.drive.domain;

import lombok.Data;

@Data
public class Blob {
    private int workspaceId;
    private String blobUrl;
    private int driveId;
    private int userId;
}
