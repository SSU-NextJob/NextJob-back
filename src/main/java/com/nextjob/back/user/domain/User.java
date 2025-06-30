package com.nextjob.back.user.domain;

import com.nextjob.base.domain.Domain;

public class User extends Domain {

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
}
