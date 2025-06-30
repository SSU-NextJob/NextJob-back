package com.nextjob.back.user.service;

import com.nextjob.back.user.web.UserSearchCriteria;
import com.nextjob.base.util.CamelCaseMap;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    /* 사용자 상세 조회 */
    List<CamelCaseMap> findUserDetail(UserSearchCriteria userSearchCriteria);
}