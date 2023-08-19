package com.zerobase.travel.accommodationreview.controller;

import com.zerobase.travel.accommodationreview.dto.AccommodationReviewDto;
import com.zerobase.travel.accommodationreview.dto.PostsRequestDto;
import com.zerobase.travel.accommodationreview.dto.UpdateRequestDto;
import com.zerobase.travel.accommodationreview.service.AccommodationReviewService;
import com.zerobase.travel.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RequestMapping
@Slf4j
@RestController
public class AccommodationReviewController {

    private final AccommodationReviewService accommodationReviewService;

    @GetMapping("/accommodation/{roomId}/review")
    public ResponseEntity<List<AccommodationReviewDto>> accommodationReviewList(@PathVariable("roomId") int id) {

        //todo: 숙소 리뷰 조회
        List<AccommodationReviewDto> accommodationReviewDto = accommodationReviewService.reviewList(id);

        try {
            return new ResponseEntity<>(accommodationReviewDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(accommodationReviewDto, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/accommodation/{roomId}/review")
    public ResponseEntity accommodationReviewPost(
            @PathVariable("roomId") int roomId,
            @RequestBody PostsRequestDto postsRequestDto,
            @AuthenticationPrincipal UserDTO user) {


        //todo: 별점 조건
        if (postsRequestDto.getGrade() <= 0 || postsRequestDto.getGrade() > 5) {
            return new ResponseEntity("별점을 다시 입력해주세요.", HttpStatus.BAD_REQUEST);
        }

        //todo: 사용자 로그인 되어 있는지 확인
        if (user == null) {
            return new ResponseEntity("로그인을 해주세요.", HttpStatus.FORBIDDEN);
        }


        //todo: 숙소 리뷰 등록
        PostsRequestDto requestDto = PostsRequestDto.builder()
                .roomId(roomId)
                .userId(user.getUserId())
                .content(postsRequestDto.getContent())
                .writer(postsRequestDto.getWriter())
                .grade(postsRequestDto.getGrade())
                .build();
        try {
            accommodationReviewService.reviewSave(roomId, requestDto);
            return new ResponseEntity("리뷰 등록에 완료되었습니다.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("리뷰 등록에 실패하였습니다. 리뷰 내용, 작성자를 모두 입력해주세요.", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/accommodation/{roomId}/review/{reviewId}")
    public ResponseEntity accommodationReviewUpdate(
            @PathVariable("reviewId") int reviewId,
            @RequestBody UpdateRequestDto updateRequestDto,
            @AuthenticationPrincipal UserDTO user) {


        //todo: 사용자 로그인 되어 있는지 확인
        if (user == null) {
            return new ResponseEntity("로그인을 해주세요.", HttpStatus.FORBIDDEN);
        }

        //todo: 리뷰 작성자와 사용자가 같은지 확인
        if (!accommodationReviewService.reviewUserCheck(reviewId, user)) {
            return new ResponseEntity("리뷰 수정 권한이 없습니다. 작성자가 본인인지 확인해주세요.", HttpStatus.FORBIDDEN);
        }


        //todo: 숙소 리뷰 수정
        try {
            if (accommodationReviewService.reviewUpdate(reviewId, updateRequestDto)) {
                return new ResponseEntity("리뷰가 수정되었습니다.", HttpStatus.OK);
            } else {
                return new ResponseEntity("리뷰가 존재하지 않습니다. 다시 확인해주세요", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity("리뷰 수정에 실패하였습니다. 다시 시도해주세요.", HttpStatus.BAD_REQUEST);
        }


    }

    @DeleteMapping("/accommodation/{roomId}/review/{reviewId}")
    public ResponseEntity accommodationReviewDelete(
            @PathVariable("reviewId") int reviewId,
            @AuthenticationPrincipal UserDTO user) {

        //todo: 사용자 로그인 되어 있는지 확인
        if (user == null) {
            return new ResponseEntity("로그인을 해주세요.", HttpStatus.FORBIDDEN);
        }

        //todo: 리뷰 작성자와 사용자가 같은지 확인
        if (!accommodationReviewService.reviewUserCheck(reviewId, user)) {
            return new ResponseEntity("리뷰 삭제 권한이 없습니다. 작성자가 본인인지 확인해주세요.", HttpStatus.FORBIDDEN);
        }

        //todo: 숙소 리뷰 삭제
        try {
            if (accommodationReviewService.reviewDelete(reviewId)) {
                return new ResponseEntity("리뷰가 삭제되었습니다.", HttpStatus.OK);
            } else {
                return new ResponseEntity("리뷰가 존재하지 않습니다. 다시 확인해주세요", HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>("리뷰 삭제에 실패하였습니다.", HttpStatus.NOT_FOUND);
        }
    }


}
