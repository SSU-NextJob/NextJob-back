package com.nextjob.back.post.service.impl;

import com.nextjob.back.post.domain.Post;
import com.nextjob.back.post.service.PostMapper;
import com.nextjob.back.post.service.PostService;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {
    private PostMapper boardMapper;

    public PostServiceImpl(PostMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    @Override
    public int insertPost(Post post) {
        return boardMapper.insertPost(post);
    }

}
