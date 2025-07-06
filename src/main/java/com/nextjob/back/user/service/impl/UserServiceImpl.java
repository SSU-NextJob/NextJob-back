package com.nextjob.back.user.service.impl;

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
     * @param userSearchCriteria
     * @return
     */
    @Override
    public List<CamelCaseMap> findUserDetail(UserSearchCriteria userSearchCriteria) {
        return userMapper.findUserDetail(userSearchCriteria);
    }

    @Override
    public CamelCaseMap findUserDetailByUserId(int userId) {
        return userMapper.findUserDetailByUserId(userId);
    }
}
