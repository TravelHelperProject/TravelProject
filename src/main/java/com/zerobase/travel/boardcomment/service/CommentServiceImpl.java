package com.zerobase.travel.boardcomment.service;

import com.zerobase.travel.boardcomment.dto.CommentDTO;
import com.zerobase.travel.boardcomment.mapper.BoardCommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final BoardCommentMapper mapper;

    @Override
    public boolean insert(CommentDTO commentDTO) {
        return mapper.insertComment(commentDTO) == 1;
    }

    @Override
    public List<CommentDTO> list(long boardId) {
        return mapper.listComment(boardId);
    }

    @Override
    public boolean modify(long boardId, long replyId, CommentDTO commentDTO) {
        return mapper.modifyComment(boardId , replyId, commentDTO) == 1;
    }

    @Override
    public boolean delete(long boardId, long replyId) {
        return mapper.deleteComment(boardId , replyId) == 1;
    }
    @Override
    public CommentDTO get(long replyId) {
        return mapper.getComment(replyId);
    }
}
