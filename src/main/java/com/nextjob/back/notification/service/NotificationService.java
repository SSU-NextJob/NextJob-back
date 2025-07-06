package com.nextjob.back.notification.service;

import com.nextjob.base.util.CamelCaseMap;

import java.util.List;

public interface NotificationService {

    /* 사용자 알림 조회 */
    List<CamelCaseMap> findNotificationsByUserId(int userId);
}
