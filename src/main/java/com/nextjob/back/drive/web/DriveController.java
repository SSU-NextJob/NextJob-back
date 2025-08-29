package com.nextjob.back.drive.web;

import com.nextjob.back.drive.service.DriveService;
import com.nextjob.back.kanbanBoard.service.KanbanService;
import com.nextjob.back.kanbanBoard.web.KanbanSearchCriteria;
import com.nextjob.base.exception.CustomException;
import com.nextjob.base.exception.ErrorCode;
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
                                                       @RequestParam("file") MultipartFile file) {
        Map<String, Object> result = new HashMap<>();

        driveService.uploadFile(driveId, file);

        return ApiResponse.ok(result);
    }

}
