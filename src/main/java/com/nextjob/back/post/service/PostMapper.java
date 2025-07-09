package com.nextjob.back.post.service;

import com.nextjob.back.post.domain.Post;
import com.nextjob.back.post.web.PostSearchCriteria;
import com.nextjob.base.util.CamelCaseMap;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {
    /* 게시글 생성 */
    int insertPost(Post post);
    /* 게시글 목록 */
    List<PostSearchCriteria> findPostList(CamelCaseMap param);
    /* 게시글 상세 */
    PostSearchCriteria findPostDetail(int postId);
}
