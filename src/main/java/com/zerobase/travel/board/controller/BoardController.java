package com.zerobase.travel.board.controller;

import com.zerobase.travel.board.dto.AddBoardDTO;
import com.zerobase.travel.board.dto.BoardDTO;
import com.zerobase.travel.board.dto.Criteria;
import com.zerobase.travel.board.dto.ModifyBoardDTO;
import com.zerobase.travel.board.exception.BoardFailException;
import com.zerobase.travel.board.exception.NotFoundException;
import com.zerobase.travel.board.service.BoardServiceImpl;
import com.zerobase.travel.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardServiceImpl boardService;

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handlerNotFoundException(NotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @GetMapping("/board/list") // 게시글 리스트 조회
    public ResponseEntity boardList() {

            if(boardService.list().isEmpty()) {
                throw new NotFoundException("조회된 게시글이 없습니다.");
            }

            List<BoardDTO> list = boardService.list();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/board/{boardId}") // 게시글 상세조회
    public ResponseEntity<BoardDTO> boardGet(@PathVariable long boardId) {

        if(boardService.get(boardId) == null) {
            throw new NotFoundException("조회된 게시글이 없습니다.");
        }

        BoardDTO board = boardService.get(boardId);

        return ResponseEntity.status(HttpStatus.OK).body(board);

    }

    @PutMapping("/board/{boardId}") // 게시글 수정
    public ResponseEntity<String> boardModify(@PathVariable long boardId,
                                              @RequestBody ModifyBoardDTO boardDTO,
                                              @AuthenticationPrincipal UserDTO user) {
        if(boardService.get(boardId).getUserId() == user.getUserId()) {
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
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("동일한 아이디가 아닙니다.");

    }

    @DeleteMapping("/board/{boardId}") // 게시글 삭제
    public ResponseEntity<String> boardDelete(@PathVariable long boardId,
                                              @AuthenticationPrincipal UserDTO user) {
        if(boardService.get(boardId).getUserId() == user.getUserId()) {
            if (boardService.delete(boardId)) {
                return ResponseEntity.status(HttpStatus.CREATED).body("삭제에 성공했습니다.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("삭제에 실패했습니다.");
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("동일한 아이디가 아닙니다.");
    }
    @ExceptionHandler(BoardFailException.class)
    public ResponseEntity<String> handlerBoardFailException(BoardFailException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/board") // 게시글 등록
    public ResponseEntity<String> boardInsert(@RequestBody AddBoardDTO addBoard, @AuthenticationPrincipal UserDTO user) {
        BoardDTO boardDTO = BoardDTO.builder()
                .boardId(addBoard.getBoardId())
                .userId(user.getUserId())
                .title(addBoard.getTitle())
                .writer(user.getNickname())
                .content(addBoard.getContent())
                .createdAt(LocalDate.now())
                .readCnt(addBoard.getReadCnt())
                .build();
        if(boardDTO.getTitle().length() > 10) {
            throw new BoardFailException("제목 글자는 10글자 이상 되면 안됩니다.");
        }

        if (boardService.insert(boardDTO)) {
            return ResponseEntity.status(HttpStatus.CREATED).body("게시글 등록 성공");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("게시글 등록 실패");
        }
    }
    @GetMapping("/board/search") // 게시글 키워드 검색
    public ResponseEntity<List<BoardDTO>> boardSearch(@RequestBody Criteria cri) {

        if(boardService.search(cri).isEmpty()) {
            throw new NotFoundException("조회된 게시글이 없습니다.");
        }

        List<BoardDTO> list= boardService.search(cri);

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

}
