package com.zerobase.travel.festival_review.controller;

import com.zerobase.travel.festival.execption.FestivalFailException;
import com.zerobase.travel.festival_review.dto.FestivalReviewAddDTO;
import com.zerobase.travel.festival_review.dto.FestivalReviewDTO;
import com.zerobase.travel.festival_review.dto.FestivalReviewModifyDTO;
import com.zerobase.travel.festival_review.service.FestivalReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class FestivalReviewController {


    private final FestivalReviewService service;

    @ExceptionHandler(FestivalFailException.class)
    public ResponseEntity handlerFestivalFailException(FestivalFailException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/festival/{festivalId}/reviewlist") // 축제별 리뷰글 리스트
    public ResponseEntity<List<FestivalReviewDTO>> reviewList(@PathVariable long festivalId) {

        List<FestivalReviewDTO> list = service.list(festivalId);
        if(list.isEmpty()) {
            throw new FestivalFailException("축제 리뷰글이 없습니다.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PostMapping("/festival/{festivalId}/review") // 축제 리뷰글 등록
    public ResponseEntity<String> reviewInsert(@RequestBody FestivalReviewAddDTO reviewDTO, @PathVariable long festivalId) {
        FestivalReviewDTO festivalReviewDTO = FestivalReviewDTO.builder()
                .reviewId(reviewDTO.getReviewId())
                .festivalId(festivalId)
                .userId(reviewDTO.getUserId())
                .content(reviewDTO.getContent())
                .writer(reviewDTO.getWriter())
                .grade(reviewDTO.getGrade())
                .createDate(LocalDate.now())
                .build();

        if(festivalReviewDTO.getGrade() > 6 && festivalReviewDTO.getGrade() < 0)  {
            throw new FestivalFailException("평점 점수는 1점에서 5점 사이만 줄 수 있습니다.");
        }


        if (service.insert(festivalReviewDTO)) {
            return ResponseEntity.status(HttpStatus.CREATED).body("리뷰글 등록 성공");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("리뷰글 등록 실페");
        }
    }

    @PutMapping("/festival/{festivalId}/review/{reviewId}") // 리뷰글 수정
    public ResponseEntity<String> reviewModify(@RequestBody FestivalReviewModifyDTO dto,
                                               @PathVariable long festivalId,
                                               @PathVariable long reviewId) {
        FestivalReviewDTO festivalReviewDTO = FestivalReviewDTO.builder()
                .reviewId(reviewId)
                .festivalId(festivalId)
                .content(dto.getContent())
                .grade(dto.getGrade())
                .build();

        if(service.modify(festivalReviewDTO)) {
            return ResponseEntity.status(HttpStatus.CREATED).body("리뷰글 수정 성공");
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body("리뷰글 수정 실패");
        }
    }
    @DeleteMapping("/festival/{festivalId}/review/{reviewId}") // 리뷰글 삭제
    public ResponseEntity<String> reviewDelete(@PathVariable long festivalId,
                                               @PathVariable long reviewId) {

        if(service.delete(festivalId,reviewId)) {
            return ResponseEntity.status(HttpStatus.CREATED).body("리뷰글 삭제 성공");
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body("이미 삭제되거나 없는 글입니다.");
        }
    }
}
