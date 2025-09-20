package com.nextjob.back.drive.web;

import com.nextjob.back.drive.service.DriveService;
import com.nextjob.base.util.CamelCaseMap;
import com.nextjob.base.web.response.ApiResponse;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/drives")
public class DriveController {

    private final DriveService driveService;

    public DriveController(DriveService driveService) {
        this.driveService = driveService;
    }

    /**
     * 파일 업로드
     *
     * @return
     */
    @PostMapping("/{driveId}/blobs/upload")
    public ApiResponse<Map<String, Object>> uploadFile(@PathVariable("driveId") int driveId,
                                                       @RequestPart("file") MultipartFile file,
                                                       @RequestParam("userId") int userId) {
        Map<String, Object> result = new HashMap<>();

        driveService.uploadFile(driveId, file, userId);

        return ApiResponse.ok(result);
    }

    /**
     * 문서 목록 조회
     *
     * @param driveId
     * @return
     */
    @GetMapping("/{driveId}/blobs")
    public ApiResponse<List<CamelCaseMap>> findFile(@PathVariable("driveId") int driveId,
                                                    @RequestParam(required = false) String search) {
        List<CamelCaseMap> fileList = driveService.findFileList(driveId, search);
        if (ObjectUtils.isEmpty(fileList)) {
            fileList = new ArrayList<>();
        }
        return ApiResponse.ok(fileList);
    }

    /**
     * 문서 삭제
     *
     * @param blobId
     * @return
     */
    @DeleteMapping("/blobs/{blobId}")
    public ApiResponse<Map<String, Object>> deleteFile(@PathVariable("blobId") int blobId) {
        Map<String, Object> result = new HashMap<>();
        result = driveService.deleteFile(blobId);
        return ApiResponse.ok(result);
    }

}
