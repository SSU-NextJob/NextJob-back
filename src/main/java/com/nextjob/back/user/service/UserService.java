package com.nextjob.back.user.service;

import com.nextjob.back.user.web.UserSearchCriteria;
import com.nextjob.base.util.CamelCaseMap;

import java.util.List;

public interface UserService {

    /* 사용자 상세 조회 */
    List<CamelCaseMap> findUserDetail(UserSearchCriteria userSearchCriteria);

    CamelCaseMap findUserDetailByUserId(int userId);
}
