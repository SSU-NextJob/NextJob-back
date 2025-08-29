package com.nextjob.back.image.service.impl;

import com.nextjob.back.common.service.ImageUploadService;
import com.nextjob.back.image.service.ImageService;
import com.nextjob.base.exception.CustomException;
import com.nextjob.base.exception.ErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageUploadService imageUploadService;

    public ImageServiceImpl(ImageUploadService imageUploadService) {
        this.imageUploadService = imageUploadService;
    }

    @Override
    public String uploadImage(MultipartFile file, String dirName) {
        if (file == null || file.isEmpty()) {
            throw new CustomException(ErrorCode.BAD_REQUEST);
        }

        try {
            return imageUploadService.upload(file, dirName);
        } catch (IOException e) {
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void deleteImage(String url) {
        if (url == null || url.isEmpty()) {
            throw new CustomException(ErrorCode.BAD_REQUEST);
        }

        imageUploadService.delete(url);
    }
}
