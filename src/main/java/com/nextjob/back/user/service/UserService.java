package com.nextjob.back.user.service;

import com.nextjob.back.user.web.UserSearchCriteria;
import com.nextjob.base.util.CamelCaseMap;

import java.util.List;

public interface UserService {

    /* 사용자 상세 조회 */
    CamelCaseMap findUserDetail(int userId);

    /* 사용자 목록 조회 */
    List<CamelCaseMap> findUserList(UserSearchCriteria userSearchCriteria);

    /* 프로젝트 제안(사용자한테 요청) */
    boolean suggestToUser(int userId, int projectId);

    /* 사용자 정보 수정 */
    void updateUser(int userId, String name, String techStack, String description);

    /* 사용자 노출 여부 수정 */
    void updateUserVisibility(int userId, Boolean isVisible);
}
