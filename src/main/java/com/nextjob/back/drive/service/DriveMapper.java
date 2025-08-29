package com.nextjob.back.drive.service;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DriveMapper {
    /* 드라이브 생성 */
    int insertDrive(int workspaceId, String path);
}
