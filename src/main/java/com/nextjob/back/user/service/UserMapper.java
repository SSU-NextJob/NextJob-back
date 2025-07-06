package com.nextjob.back.user.service;

import com.nextjob.back.user.web.UserSearchCriteria;
import com.nextjob.base.util.CamelCaseMap;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    /* 사용자 상세 조회 */
    List<CamelCaseMap> findUserDetail(UserSearchCriteria userSearchCriteria);

    CamelCaseMap findUserDetailByUserId(int userId);
}