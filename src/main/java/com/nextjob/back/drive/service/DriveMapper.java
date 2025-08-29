package com.nextjob.back.drive.service;

import com.nextjob.back.drive.domain.Blob;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DriveMapper {
    /* 드라이브 생성 */
    int insertDrive(int workspaceId, String path);

    /* 파일 업로드 */
    int insertBlob(Blob blob);
}
