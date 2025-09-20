package com.nextjob.back.common.service;

import io.awspring.cloud.s3.ObjectMetadata;
import io.awspring.cloud.s3.S3Resource;
import io.awspring.cloud.s3.S3Template;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service
public class ImageUploadService {

    private final S3Template s3Template;
    private final String bucketName;

    public ImageUploadService(S3Template s3Template, @Value("${spring.cloud.aws.s3.bucket}") String bucketName) {
        this.s3Template = s3Template;
        this.bucketName = bucketName;
    }

    /**
     * S3에 파일을 업로드하고 해당 파일의 URL을 반환합니다.
     * @param multipartFile 업로드할 파일
     * @param dirName S3 버킷 내에 파일을 저장할 디렉토리 이름 (e.g., "profile-images")
     * @return 업로드된 파일의 전체 S3 URL
     * @throws IOException 파일 처리 중 오류 발생 시
     */
    public String upload(MultipartFile multipartFile, String dirName) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();
        String uniqueFilename = dirName + "/" + UUID.randomUUID().toString() + "-" + originalFilename;

        // 한글 파일명 인코딩
        String encodedFilename = URLEncoder.encode(originalFilename, StandardCharsets.UTF_8.toString()).replaceAll("\\+", "%20");
        // 파일 다운로드
        String contentDisposition = "attachment; filename*=UTF-8''" + encodedFilename;

        ObjectMetadata metadata = ObjectMetadata.builder()
                .contentType(multipartFile.getContentType())
                .contentLength(multipartFile.getSize())
                .contentDisposition(contentDisposition)
                .build();

        S3Resource s3Resource = s3Template.upload(this.bucketName, uniqueFilename, multipartFile.getInputStream(), metadata);

        return s3Resource.getURL().toString();
    }

    /**
     * S3에서 기존 이미지 파일을 삭제하는 메소드
     * @param fileUrl 삭제할 파일의 전체 S3 URL
     */
    public void delete(String fileUrl) {
        if (!StringUtils.hasText(fileUrl)) {
            return;
        }

        try {
            String key = extractKeyFromUrl(fileUrl);
            s3Template.deleteObject(bucketName, key);
        } catch (Exception e) {
            System.err.println("S3 file delete failed: " + fileUrl + ", error: " + e.getMessage());
        }
    }

    /**
     * 전체 S3 URL에서 객체 키(Key)를 추출하는 헬퍼 메소드
     */
    private String extractKeyFromUrl(String fileUrl) {
        try {
            URI uri = new URI(fileUrl);
            String path = uri.getPath();
            return path.substring(1);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid S3 URL format", e);
        }
    }
}
