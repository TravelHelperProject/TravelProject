package com.zerobase.travel.board.service;

import com.zerobase.travel.board.dto.BoardDTO;
import com.zerobase.travel.board.dto.Criteria;

import java.util.List;

public interface BoardService {
    public List<BoardDTO> list();

    public BoardDTO get(long boardId);

    public boolean modify(long boardId ,BoardDTO boardDTO);

    public boolean delete(long boardId);

    public boolean insert(BoardDTO boardDTO);

    public List<BoardDTO> search(Criteria cri);
}
