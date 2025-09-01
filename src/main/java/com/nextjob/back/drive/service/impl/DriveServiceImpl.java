package com.nextjob.back.drive.service.impl;

import com.nextjob.back.common.service.ImageUploadService;
import com.nextjob.back.drive.domain.Blob;
import com.nextjob.back.image.service.ImageService;
import com.nextjob.base.exception.CustomException;
import com.nextjob.base.exception.ErrorCode;

import com.nextjob.back.drive.service.DriveMapper;
import com.nextjob.back.drive.service.DriveService;
import com.nextjob.base.util.CamelCaseMap;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DriveServiceImpl implements DriveService {

    private final DriveMapper driveMapper;
    private final ImageUploadService imageUploadService;
    private final ImageService imageService;

    public DriveServiceImpl(DriveMapper driveMapper, ImageUploadService imageUploadService, ImageService imageService) {
        this.driveMapper = driveMapper;
        this.imageUploadService = imageUploadService;
        this.imageService = imageService;
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

    /**
     * 문서 목록 조회
     *
     * @param driveId
     * @return
     */
    @Override
    public List<CamelCaseMap> findFileList(int driveId, String search) {
        return driveMapper.findFileList(driveId, search);
    }

    /**
     * 문서 삭제
     *
     * @param blobId
     * @return
     */
    @Override
    public Map<String, Object> deleteFile(int blobId) {
        Map<String, Object> result = new HashMap<>();

        // S3에서 삭제해줄 url 가져오기
        String url = driveMapper.findUrl(blobId);

        // S3 파일 삭제
        imageService.deleteImage(url);

        // blobs 테이블 delete_date UPDATE
        int deletedCount = driveMapper.deleteFile(blobId);

        if(deletedCount > 0) {
            result.put("success", true);
            result.put("message", "삭제되었습니다.");
        } else {
            throw new CustomException(ErrorCode.DELETE_ERROR);
        }

        return result;
    }
}
