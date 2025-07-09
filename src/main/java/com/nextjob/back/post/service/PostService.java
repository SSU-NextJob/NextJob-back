package com.nextjob.back.post.service;

import com.nextjob.back.post.domain.Post;
import com.nextjob.back.post.web.PostDetailResponse;
import com.nextjob.back.post.web.PostListResponse;
import com.nextjob.base.util.CamelCaseMap;

import java.util.List;

public interface PostService {
    List<PostListResponse> findPostList(CamelCaseMap param);
    PostDetailResponse findPostDetail(int postId);
    int insertPost(Post post);
    int updatePost(Post post);
    int deletePost(int postId);
}
