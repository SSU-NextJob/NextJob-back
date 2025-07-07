package com.nextjob.back.board.service.impl;

import com.nextjob.back.board.domain.Post;
import com.nextjob.back.board.service.BoardMapper;
import com.nextjob.back.board.service.BoardService;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {
    private BoardMapper boardMapper;

    public BoardServiceImpl(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    @Override
    public int insertPost(Post post) {
        return boardMapper.insertPost(post);
    }

}
