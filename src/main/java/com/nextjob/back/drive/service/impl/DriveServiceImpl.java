package com.nextjob.back.drive.service.impl;

import com.nextjob.back.common.service.ImageUploadService;
import com.nextjob.back.drive.domain.Blob;
import com.nextjob.base.exception.CustomException;
import com.nextjob.base.exception.ErrorCode;

import com.nextjob.back.drive.service.DriveMapper;
import com.nextjob.back.drive.service.DriveService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class DriveServiceImpl implements DriveService {

    private final DriveMapper driveMapper;
    private final ImageUploadService imageUploadService;

    public DriveServiceImpl(DriveMapper driveMapper, ImageUploadService imageUploadService) {
        this.driveMapper = driveMapper;
        this.imageUploadService = imageUploadService;
    }

    @Override
    public void uploadFile(int driveId, MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new CustomException(ErrorCode.BAD_REQUEST);
        }

        String dirName = "drives/" + driveId;
        String fileURL;

        try {
            fileURL = imageUploadService.upload(file, dirName);
        } catch (IOException e) {
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }

        Blob blob = new Blob();
        blob.setDriveId(driveId);
        blob.setBlobUrl(fileURL);

        driveMapper.insertBlob(blob);
    }
}
