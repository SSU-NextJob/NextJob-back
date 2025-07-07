package com.nextjob.back.board.service;

import com.nextjob.back.board.domain.Post;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {
    /* 프로젝트 생성 */
    int insertPost(Post post);
}
