package com.zerobase.travel.board.controller;

import com.zerobase.travel.board.dto.AddBoardDTO;
import com.zerobase.travel.board.dto.BoardDTO;
import com.zerobase.travel.board.dto.Criteria;
import com.zerobase.travel.board.dto.ModifyBoardDTO;
import com.zerobase.travel.board.service.BoardServiceImpl;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Log
@Builder
public class BoardController {

    private final BoardServiceImpl boardService;

    @GetMapping("/board/list") // 게시글 리스트 조회
    public ResponseEntity<List<BoardDTO>> boardList() {
        List<BoardDTO> list = boardService.list();

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/board/{boardId}") // 게시글 상세조회
    public ResponseEntity<BoardDTO> boardGet(@PathVariable long boardId) {
        BoardDTO board = boardService.get(boardId);

        return ResponseEntity.status(HttpStatus.OK).body(board);

    }

    @PutMapping("/board/{boardId}") // 게시글 수정
    public ResponseEntity<String> boardModify(@PathVariable long boardId, @RequestBody ModifyBoardDTO boardDTO) {

        BoardDTO modifyBoard = BoardDTO.builder()
                .title(boardDTO.getTitle())
                .content(boardDTO.getContent())
                .updateDate(LocalDate.now())
                .build();

        if (boardService.modify(boardId, modifyBoard)) {
            return ResponseEntity.status(HttpStatus.CREATED).body("수정에 성공했습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("수정에 실패했습니다.");
        }


    }

    @DeleteMapping("/board/{boardId}") // 게시글 삭제
    public ResponseEntity<String> boardDelete(@PathVariable long boardId) {

        if (boardService.delete(boardId)) {
            return ResponseEntity.status(HttpStatus.CREATED).body("삭제에 성공했습니다.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("삭제에 실패했습니다.");
        }

    }

    @PostMapping("/board") // 게시글 등록
    public ResponseEntity<String> boardInsert(@RequestBody AddBoardDTO addBoard) {
        BoardDTO boardDTO = BoardDTO.builder()
                .boardId(addBoard.getBoardId())
                .userId(addBoard.getUserId())
                .title(addBoard.getTitle())
                .writer(addBoard.getWriter())
                .content(addBoard.getContent())
                .createdAt(LocalDate.now())
                .readCnt(addBoard.getReadCnt())
                .build();

        if (boardService.insert(boardDTO)) {
            return ResponseEntity.status(HttpStatus.CREATED).body("게시글 등록 성공");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("게시글 등록 실패");
        }
    }
    @GetMapping("/board/search") // 게시글 키워드 검색
    public ResponseEntity<List<BoardDTO>> boardSearch(@RequestBody String keyword) {

        Criteria cri = new Criteria();
        cri.setKeyword(keyword);

        List<BoardDTO> list= boardService.search(cri);

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

}
