package com.zerobase.travel.board.mapper;

import com.zerobase.travel.board.dto.BoardDTO;
import com.zerobase.travel.board.dto.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    public List<BoardDTO> listBoard();

    public BoardDTO getBoard(long boardId);

    public int modifyBoard(long boardId,BoardDTO boardDTO);

    public int deleteBoard(long boardId);

    public int insertBoard(BoardDTO boardDTO);

    public List<BoardDTO> searchBoard(Criteria keyword);

    public int readCntBoard(long boardId);

}
