package com.nextjob.back.image.web;

import com.nextjob.back.image.service.ImageService; // 아래에서 만들 서비스
import com.nextjob.base.web.response.ApiResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/images")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    /**
     * 단일 이미지 업로드 API
     * @param file 업로드할 이미지 파일
     * @return 업로드된 이미지의 S3 URL
     */
    @PostMapping("/upload")
    public ApiResponse<Map<String, String>> uploadImage(
            @RequestPart("file") MultipartFile file,
            @RequestParam("type") String type
    ) {
        String dirName = null;
        if (type.equals("profile")) {
            dirName = "profile-images";
        } else {
            dirName = "project-images";
        }

        // 이미지 S3 업로드
        String imageUrl = imageService.uploadImage(file, dirName);

        Map<String, String> response = new HashMap<>();
        response.put("imageUrl", imageUrl);

        return ApiResponse.ok(response);
    }
}
