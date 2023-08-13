package com.zerobase.travel.boardcomment.controller;

import com.zerobase.travel.board.dto.ModifyBoardDTO;
import com.zerobase.travel.boardcomment.dto.AddCommentDTO;
import com.zerobase.travel.boardcomment.dto.CommentDTO;
import com.zerobase.travel.boardcomment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService service;

    //댓글 등록
    @PostMapping("/board/{boardId}/comment")
    public ResponseEntity<String> commentInsert(@PathVariable long boardId, @RequestBody AddCommentDTO commentDTO) {

        CommentDTO comment = CommentDTO.builder()
                .userId(commentDTO.getUserId())
                .replyId(commentDTO.getReplyId())
                .boardId(boardId)
                .content(commentDTO.getContent())
                .createdAt(LocalDate.now())
                .build();
        if (service.insert(comment)) {
            return ResponseEntity.status(HttpStatus.CREATED).body("댓글 등록 성공");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("댓글 등록 실패");
        }
    }
    //댓글조회
    @GetMapping("/board/{boardId}/comment")
    public ResponseEntity<List<CommentDTO>> commentList(@PathVariable long boardId){
        List<CommentDTO> commentDTO = service.list(boardId);
         return ResponseEntity.status(HttpStatus.OK).body(commentDTO);

    }
    //댓글 수정
    @PutMapping("/board/{boardId}/comment/{replyId}")
    public ResponseEntity<String> commentModify(@PathVariable long boardId, @PathVariable long replyId, @RequestBody ModifyBoardDTO modifyBoardDTO) {
        CommentDTO commentDTO = CommentDTO.builder()
                .content(modifyBoardDTO.getContent())
                .build();
        if(service.modify(boardId,replyId, commentDTO)) {
            return ResponseEntity.status(HttpStatus.CREATED).body("댓글 수정 성공");
        } else  {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("댓글 수정 실패");
        }
    }
    //댓글 삭제
    @DeleteMapping("/board/{boardId}/comment/{replyId}")
    public ResponseEntity<String> commentDelete(@PathVariable long boardId, @PathVariable long replyId) {
        if(service.delete(boardId, replyId)) {
            return ResponseEntity.status(HttpStatus.CREATED).body("댓글 삭제 성공");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("댓글 삭제 실패");
        }
    }

}
