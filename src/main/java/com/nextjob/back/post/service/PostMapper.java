package com.nextjob.back.post.service;

import com.nextjob.back.post.domain.Post;
import com.nextjob.back.post.web.PostDetailResponse;
import com.nextjob.back.post.web.PostListResponse;
import com.nextjob.base.util.CamelCaseMap;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {
    /* 게시글 생성 */
    int insertPost(Post post);
    /* 게시글 목록 */
    List<PostListResponse> findPostList(CamelCaseMap param);
    /* 게시글 상세 */
    PostDetailResponse findPostDetail(int postId);
}
