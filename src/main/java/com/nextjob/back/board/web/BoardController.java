package com.nextjob.back.board.web;

import com.nextjob.back.board.domain.Post;
import com.nextjob.back.board.service.BoardService;
import com.nextjob.back.project.domain.Project;
import com.nextjob.base.util.CamelCaseMap;
import com.nextjob.base.web.response.ApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/board")
public class BoardController {
    private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    /**
     * 프로젝트 생성
     *
     * @param post
     * @return
     */
    @PostMapping("/insert")
    public ApiResponse<CamelCaseMap> insertProject(@RequestBody Post post) {
        int insertedCount = boardService.insertPost(post);
        return ApiResponse.ok(null);
    }
}
