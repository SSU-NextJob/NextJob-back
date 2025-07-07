package com.nextjob.back.user.service;

import com.nextjob.base.util.CamelCaseMap;

import java.util.List;

public interface UserService {

    /* 사용자 상세 조회 */
    CamelCaseMap findUserDetail(int userId);

    CamelCaseMap findUserDetailByUserId(int userId);

    /* 사용자 목록 조회 */
    List<CamelCaseMap> findUserList(String userType, String search);

    /* 프로젝트 제안(사용자한테 요청) */
    boolean suggestToUser(int userId, int projectId);

    /* 사용자 정보 수정 */
    void updateUser(int userId, String name, String techStack, String description);
}
