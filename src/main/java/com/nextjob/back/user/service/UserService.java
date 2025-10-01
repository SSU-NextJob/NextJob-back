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
    void updateUser(UserSearchCriteria userSearchCriteria);

    /* 사용자 노출 여부 수정 */
    void updateUserVisibility(int userId, Boolean isVisible);

    /* 사용자 생성 */
    void createUser(String name, String email);

    /* 사용자 조회 (구글) */
    CamelCaseMap findUserDetailByEmail(String email);

    /* 액세스 토큰 저장 */
    void updateUserAccessToken(int userId, String accessToken);
}
