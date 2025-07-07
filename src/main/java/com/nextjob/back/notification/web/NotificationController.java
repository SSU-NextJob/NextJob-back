package com.nextjob.back.notification.web;

import com.nextjob.back.notification.service.NotificationService;
import com.nextjob.back.user.service.UserService;
import com.nextjob.base.exception.CustomException;
import com.nextjob.base.exception.ErrorCode;
import com.nextjob.base.util.CamelCaseMap;
import com.nextjob.base.web.response.ApiResponse;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/notifications")
public class NotificationController {
    private final NotificationService notificationService;
    private final UserService userService;

    public NotificationController(
            NotificationService notificationService,
            UserService userService
    ) {
        this.notificationService = notificationService;
        this.userService = userService;
    }

    // 알림 조회
    @GetMapping
    public ApiResponse<List<CamelCaseMap>> findNotificationList(@RequestParam int userId) {
        CamelCaseMap user = userService.findUserDetail(userId);
        if (ObjectUtils.isEmpty(user)) {
            throw new CustomException(ErrorCode.NOT_FOUND);
        }

        List<CamelCaseMap> notifications = notificationService.findNotificationsByUserId(userId);
        if (notifications == null) {
            notifications = Collections.emptyList();
        }

        return ApiResponse.ok(notifications);
    }

    // 알림 확인
    @PatchMapping("/{notificationId}/read")
    public ApiResponse<CamelCaseMap> readNotification(@PathVariable("notificationId") int notificationId) {
        notificationService.readNotification(notificationId);
        return ApiResponse.ok(null);
    }

    // 알림 전체 확인
    @PatchMapping("/read-all")
    public ApiResponse<List<CamelCaseMap>> readAllNotifications(@RequestParam int userId) {
        CamelCaseMap user = userService.findUserDetail(userId);
        if (ObjectUtils.isEmpty(user)) {
            throw new CustomException(ErrorCode.NOT_FOUND);
        }

        notificationService.readNotificationsByUserId(userId);
        return ApiResponse.ok(null);
    }
}
