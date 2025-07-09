package com.nextjob.back.post.service;

import com.nextjob.back.post.domain.Post;
import com.nextjob.back.post.web.PostSearchCriteria;
import com.nextjob.base.util.CamelCaseMap;

import java.util.List;
import java.util.Map;

public interface PostService {
    int insertPost(Post post);
    List<PostSearchCriteria> findPostList(CamelCaseMap param);
    PostSearchCriteria findPostDetail(int postId);
}
