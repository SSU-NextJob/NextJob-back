package com.nextjob.back.notification.service;

import com.nextjob.back.notification.domain.Notification;
import com.nextjob.base.util.CamelCaseMap;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NotificationMapper {

    /* 사용자 알림 조회 */
    List<CamelCaseMap> findNotificationsByUserId(int userId);

    CamelCaseMap findNotificationByNotificationId(int notificationId);

    void markNotificationAsRead(int notificationId);

    void readAllNotificationsByUserId(int userId);

    int insertNotification(Notification notification);
}
