package com.nextjob.back.notification.web;

import com.nextjob.base.domain.Domain;
import com.nextjob.base.web.servlet.search.Search;

@SuppressWarnings("serial")
public class NotificationSearchCriteria extends Domain implements Search {

    private int notificationId;

    private int userId;

    private String title;

    private String content;

    private Boolean isRead;

    private String techStack;

    private String profileImage;

    //    TODO: 타입 수정 필요
    private String notificationType;

}
