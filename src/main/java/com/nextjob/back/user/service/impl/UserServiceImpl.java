package com.nextjob.back.user.service.impl;

import com.nextjob.back.notification.domain.Notification;
import com.nextjob.back.user.service.UserMapper;
import com.nextjob.back.user.service.UserService;
import com.nextjob.base.util.CamelCaseMap;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 사용자 상세 조회
     *
     * @param
     * @return
     */
    @Override
    public CamelCaseMap findUserDetail(int userId) {
        return userMapper.findUserDetail(userId);
    }

    @Override
    public CamelCaseMap findUserDetailByUserId(int userId) {
        return userMapper.findUserDetailByUserId(userId);
    }


    /**
     * 사용자 목록 조회
     *
     * @param
     * @return
     */
    @Override
    public List<CamelCaseMap> findUserList(String userType, String search) {
        return userMapper.findUserList(userType, search);
    }

    @Override
    public boolean suggestToUser(int userId, int projectId) {
        String requestType = "SUGGEST";     // 요청종류 : 제안
        String requestStatus = "PENDING";   // 요청상태 : 대기

        // 프로젝트 제안(사용자한테 요청) -> 프로젝트 제안/지원 테이블에 insert 후 id값 리턴
        int requestId = userMapper.insertSuggestRequest(userId, projectId, requestType, requestStatus);

        if (requestId < 1) {
            return false;
        }

        // 프로젝트 제안 알림 등록
        Notification notification = new Notification();
        notification.setTitle("프로젝트 제안 요청");
        notification.setContent("프로젝트 제안을 요청드립니다.");
        notification.setNotificationType("SUGGEST_NOTIFICATION");
        notification.setUserId(userId);

        return userMapper.insertNotification(notification) > 0;
    }
}

