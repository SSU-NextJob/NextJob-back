package com.nextjob.back.post.web;

import com.nextjob.back.post.domain.Post;
import com.nextjob.back.post.service.PostService;
import com.nextjob.base.util.CamelCaseMap;
import com.nextjob.base.web.response.ApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/posts")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    /**
     * 프로젝트 생성
     *
     * @param post
     * @return
     */
    @PostMapping("/insert")
    public ApiResponse<CamelCaseMap> insertProject(@RequestBody Post post) {
        int insertedCount = postService.insertPost(post);
        return ApiResponse.ok(null);
    }
}
