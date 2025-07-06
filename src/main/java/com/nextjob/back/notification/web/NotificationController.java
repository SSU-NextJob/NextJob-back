package com.nextjob.back.notification.web;

import com.nextjob.back.notification.domain.Notification;
import com.nextjob.back.notification.service.NotificationService;
import com.nextjob.base.util.CamelCaseMap;
import com.nextjob.base.web.response.ApiResponse;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/notifications")
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    // 알림 조회
    @GetMapping
    public ApiResponse<List<CamelCaseMap>> findNotificationList(@RequestParam int userId, Model model) {
        List<CamelCaseMap> notifications = notificationService.findNotificationsByUserId(userId);
        if (notifications == null) {
            notifications = Collections.emptyList();
        }
        return ApiResponse.ok(notifications);
    }

    // 알림 확인
    @PatchMapping("/{notificationId}/read")
    public String readNotification(@PathVariable("notificationId") int notificationId, Model model) {
        return null;
    }

    // 알림 전체 확인
    @PatchMapping("/read-all")
    public String readAllNotifications(Model model) {
        return null;
    }
}
