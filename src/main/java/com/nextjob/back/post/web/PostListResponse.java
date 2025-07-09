package com.nextjob.back.post.web;

import com.nextjob.back.project.web.ProjectInfo;
import com.nextjob.base.web.servlet.search.Search;

import java.util.Date;

@SuppressWarnings("serial")
public class PostListResponse implements Search {
    private int postId;
    private String title;
    private String roleType;
    private Date createDate;
    private int userId;
    private String userName;
    private ProjectInfo project;

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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

    public ProjectInfo getProject() {
        return project;
    }

    public void setProject(ProjectInfo project) {
        this.project = project;
    }
}
