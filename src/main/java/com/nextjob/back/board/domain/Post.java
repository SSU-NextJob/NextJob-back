package com.nextjob.back.board.domain;

import com.nextjob.base.domain.Domain;

public class Post extends Domain {
    /* 게시글 아이디 */
    int postId;

    /* 프로젝트 아이디 */
    int projectId;

    /* 사용자 아이디 */
    int userId;

    /* 사용자 이름 */
    String userName;

    /* 제목 */
    String title;

    /* 본문 */
    String content;

    /* 모집 직군 */
    String roleType;

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }
}
