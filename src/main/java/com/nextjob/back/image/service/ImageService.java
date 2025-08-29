package com.nextjob.back.image.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    String uploadImage(MultipartFile file, String dirName);

    void deleteImage(String url);
}
