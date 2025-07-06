package com.nextjob.back.notification.service.impl;

import com.nextjob.back.notification.service.NotificationMapper;
import com.nextjob.back.notification.service.NotificationService;
import com.nextjob.base.util.CamelCaseMap;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationMapper notificationMapper;

    public NotificationServiceImpl(NotificationMapper notificationMapper) {
        this.notificationMapper = notificationMapper;
    }

    @Override
    public List<CamelCaseMap> findNotificationsByUserId(int userId) {
        return notificationMapper.findNotificationsByUserId(userId);
    }

    @Override
    public CamelCaseMap readNotification(int notificationId) {
        notificationMapper.markNotificationAsRead(notificationId);
        return notificationMapper.findNotificationByNotificationId(notificationId);
    }
}
