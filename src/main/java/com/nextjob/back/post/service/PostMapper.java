package com.nextjob.back.post.service;

import com.nextjob.back.post.domain.Post;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostMapper {
    /* 프로젝트 생성 */
    int insertPost(Post post);
}
