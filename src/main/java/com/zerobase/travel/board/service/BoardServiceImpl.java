package com.zerobase.travel.board.service;

import com.zerobase.travel.board.dto.BoardDTO;
import com.zerobase.travel.board.dto.Criteria;
import com.zerobase.travel.board.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;

    @Override
    public List<BoardDTO> list() {
        return boardMapper.listBoard();
    }

    @Override
    public BoardDTO get(long boardId) {
        boardMapper.readCntBoard(boardId);
        return boardMapper.getBoard(boardId);
    }

    @Override
    @Transactional
    public boolean modify(long boardId, BoardDTO boardDTO) {


        return boardMapper.modifyBoard(boardId, boardDTO) == 1;

    }

    @Override
    public boolean delete(long boardId) {
        return boardMapper.deleteBoard(boardId) == 1;
    }

    @Override
    public boolean insert(BoardDTO boardDTO) {
        return boardMapper.insertBoard(boardDTO) == 1;
    }

    @Override
    public List<BoardDTO> search(Criteria cri) {
        return boardMapper.searchBoard(cri);
    }

}
