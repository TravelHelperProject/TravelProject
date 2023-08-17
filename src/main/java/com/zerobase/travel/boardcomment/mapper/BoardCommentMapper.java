package com.zerobase.travel.boardcomment.mapper;

import com.zerobase.travel.boardcomment.dto.CommentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardCommentMapper {
    public int insertComment(CommentDTO commentDTO);
    public List<CommentDTO> listComment(long boardId);
    public int modifyComment(long boardId, long replyId, CommentDTO commentDTO);
    public int deleteComment(long boardId, long replyId);
    CommentDTO  getComment(long replyId);
}
