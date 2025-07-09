package com.nextjob.back.post.web;

import com.nextjob.back.post.domain.Post;
import com.nextjob.back.post.service.PostService;
import com.nextjob.base.exception.CustomException;
import com.nextjob.base.exception.ErrorCode;
import com.nextjob.base.util.CamelCaseMap;
import com.nextjob.base.web.response.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    /**
     * 게시글 목록 조회
     * @param type, role, search, page, pageSize
     * @return ApiResponse
     */
    @GetMapping
    public ApiResponse<List<PostListResponse>> findPostList(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String role,
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        CamelCaseMap param = new CamelCaseMap();
        param.put("type", type);
        param.put("role", role);
        param.put("search", search);
        param.put("offset", (page - 1) * pageSize);
        param.put("limit", pageSize);

        List<PostListResponse> list = postService.findPostList(param);
        return ApiResponse.ok(list);
    }

    @GetMapping("/{id}")
    public ApiResponse<PostDetailResponse> findPostDetail(@PathVariable("id") int postId) {
        PostDetailResponse post = postService.findPostDetail(postId);
        if (post == null) {
            throw new CustomException(ErrorCode.NOT_FOUND);
        }
        return ApiResponse.ok(post);
    }

    /**
     * 프로젝트 생성
     *
     * @param post
     * @return ApiResponse
     */
    @PostMapping("/insert")
    public ApiResponse<CamelCaseMap> insertProject(@RequestBody Post post) {
        int insertedCount = postService.insertPost(post);
        return ApiResponse.ok(null);
    }

    /**
     * 게시글 수정
     *
     * @param postId, post
     * @return ApiResponse
     */
    @PutMapping("/{id}")
    public ApiResponse<?> updatePost(
            @PathVariable("id") int postId,
            @RequestBody Post post
    ) {
        post.setPostId(postId);
        int count = postService.updatePost(post);
        if (count == 0) {
            throw new CustomException(ErrorCode.NOT_FOUND);
        }
        return ApiResponse.ok(null);
    }

    /**
     * 게시글 삭제
     *
     * @param postId
     * @return ApiResponse
     */
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deletePost(@PathVariable("id") int postId) {
        int count = postService.deletePost(postId);
        if (count == 0) {
            throw new CustomException(ErrorCode.NOT_FOUND);
        }
        return ApiResponse.ok(null);
    }

}
