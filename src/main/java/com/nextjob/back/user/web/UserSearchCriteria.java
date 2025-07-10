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

    /* 개발분야 */
    private String userType;

    /* 검색어 */
    private String search;

    private int offset;

    private int limit;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTechStack() {
        return techStack;
    }

    public void setTechStack(String techStack) {
        this.techStack = techStack;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
