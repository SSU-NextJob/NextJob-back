package com.nextjob.back.post.web;

import com.nextjob.back.project.web.ProjectInfo;
import com.nextjob.base.web.servlet.search.Search;

import java.util.Date;

@SuppressWarnings("serial")
public class PostDetailResponse extends PostListResponse implements Search {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
