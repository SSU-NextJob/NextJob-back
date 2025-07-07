package com.nextjob.back.user.service;

import com.nextjob.back.notification.domain.Notification;
import com.nextjob.base.util.CamelCaseMap;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    /* 사용자 상세 조회 */
    CamelCaseMap findUserDetail(@Param("userId")int userId);

    CamelCaseMap findUserDetailByUserId(int userId);

    /* 사용자 목록 조회 */
    List<CamelCaseMap> findUserList(@Param("userType")String userType, @Param("search") String search);

    /* 프로젝트 제안(사용자한테 요청) */
    int insertSuggestRequest(@Param("userId")int userId, @Param("projectId")int projectId, @Param("requestType") String requestType, @Param("requestStatus") String requestStatus);

    /* 프로젝트 제안(사용자한테 요청) 알림 등록 */
    int insertNotification(Notification notification);

    /* 사용자 정보 수정 */
    void updateUser(
            @Param("userId") int userId,
            @Param("name") String name,
            @Param("techStack") String techStack,
            @Param("description") String description
    );

    /* 사용자 노출 여부 수정 */
    void updateUserVisibility(@Param("userId") int userId, @Param("isVisible") Boolean isVisible);
}