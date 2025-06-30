package com.nextjob.back.user.web;

import com.nextjob.base.domain.Domain;
import com.nextjob.base.web.servlet.search.Search;

@SuppressWarnings("serial")
public class UserSearchCriteria extends Domain implements Search {

    /* 사용자ID */
    private int userId;

    /* 사용자이름 */
    private String name;

    /* 이메일 */
    private String email;

    /* 설명 */
    private String description;

    /* 기술스택 */
    private String techStack;

    /* 프로필이미지 */
    private String profileImage;



}
