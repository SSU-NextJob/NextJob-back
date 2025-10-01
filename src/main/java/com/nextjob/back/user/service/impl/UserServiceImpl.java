package com.nextjob.back.user.service.impl;

import com.nextjob.back.notification.domain.Notification;
import com.nextjob.back.user.service.UserMapper;
import com.nextjob.back.user.service.UserService;
import com.nextjob.back.user.web.UserSearchCriteria;
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

    /**
     * 사용자 목록 조회
     *
     * @param
     * @return
     */
    @Override
    public List<CamelCaseMap> findUserList(UserSearchCriteria userSearchCriteria) {
        return userMapper.findUserList(userSearchCriteria);
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

    @Override
    public void updateUser(UserSearchCriteria userSearchCriteria) {
        userMapper.updateUser(userSearchCriteria);
    }

    @Override
    public void updateUserVisibility(int userId, Boolean isVisible) {
        userMapper.updateUserVisibility(userId, isVisible);
    }

    @Override
    public void createUser(String name, String email) {
        userMapper.createUser(name, email);
    }

    @Override
    public CamelCaseMap findUserDetailByEmail(String email) {
        return userMapper.findUserDetailByEmail(email);
    }

    @Override
    public void updateUserAccessToken(int userId, String accessToken) {
        userMapper.updateUserAccessToken(userId, accessToken);
    }
}

