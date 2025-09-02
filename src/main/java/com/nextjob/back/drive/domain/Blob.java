package com.nextjob.back.drive.domain;

import com.nextjob.base.domain.Domain;
import lombok.Data;

@Data
public class Blob extends Domain {
    private int workspaceId;
    private String blobUrl;
    private int driveId;
    private int userId;
    private String ext;
    private String name;
    private long size;
}
