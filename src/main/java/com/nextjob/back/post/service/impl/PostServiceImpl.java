package com.nextjob.back.post.service.impl;

import com.nextjob.back.post.domain.Post;
import com.nextjob.back.post.service.PostMapper;
import com.nextjob.back.post.service.PostService;
import com.nextjob.back.post.web.PostDetailResponse;
import com.nextjob.back.post.web.PostListResponse;
import com.nextjob.base.util.CamelCaseMap;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private PostMapper postMapper;

    public PostServiceImpl(PostMapper postMapper) {
        this.postMapper = postMapper;
    }

    @Override
    public List<PostListResponse> findPostList(CamelCaseMap param) {
        return postMapper.findPostList(param);
    }

    @Override
    public PostDetailResponse findPostDetail(int postId) {
        return postMapper.findPostDetail(postId);
    }

    @Override
    public int insertPost(Post post) {
        return postMapper.insertPost(post);
    }

    @Override
    public int updatePost(Post post) {
        return postMapper.updatePost(post);
    }

    @Override
    public int deletePost(int postId) {
        return postMapper.deletePost(postId);
    }

}
