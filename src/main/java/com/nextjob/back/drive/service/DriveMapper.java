package com.nextjob.back.drive.service;

import com.nextjob.back.drive.domain.Blob;
import com.nextjob.base.util.CamelCaseMap;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DriveMapper {
    /* 드라이브 생성 */
    int insertDrive(int workspaceId, String path);

    /* 파일 업로드 */
    int insertBlob(Blob blob);

    /* 문서 목록 조회 */
    List<CamelCaseMap> findFileList(@Param("driveId") int driveId, @Param("search") String search);

    /* S3 삭제 URL 조회 */
    String findUrl(int blobId);

    /* 문서 삭제 */
    int deleteFile(int blobId);
}
