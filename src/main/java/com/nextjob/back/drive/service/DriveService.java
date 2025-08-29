package com.nextjob.back.drive.service;

import org.springframework.web.multipart.MultipartFile;

public interface DriveService {

    void uploadFile(int driveId, MultipartFile file);
}
