package com.nextjob.back.post.service;

import com.nextjob.back.post.domain.Post;
import com.nextjob.back.post.web.PostDetailResponse;
import com.nextjob.back.post.web.PostListResponse;
import com.nextjob.base.util.CamelCaseMap;

import java.util.List;

public interface PostService {
    int insertPost(Post post);
    List<PostListResponse> findPostList(CamelCaseMap param);
    PostDetailResponse findPostDetail(int postId);
}
