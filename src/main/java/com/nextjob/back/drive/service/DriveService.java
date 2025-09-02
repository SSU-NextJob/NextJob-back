package com.nextjob.back.drive.service;

import com.nextjob.base.util.CamelCaseMap;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface DriveService {

    void uploadFile(int driveId, MultipartFile file, int userId);

    List<CamelCaseMap> findFileList(int driveId, String search);

    Map<String, Object> deleteFile(int blobId);
}
