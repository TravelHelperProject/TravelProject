package com.zerobase.travel.boardcomment.service;

import com.zerobase.travel.boardcomment.dto.CommentDTO;

import java.util.List;

public interface CommentService {

    public boolean insert(CommentDTO commentDTO);
    public List<CommentDTO> list(long boardId);
    public boolean modify(long boardId, long replyId, CommentDTO commentDTO);
    public boolean delete(long boardId, long replyId);
    public CommentDTO get(long replyId);

}
