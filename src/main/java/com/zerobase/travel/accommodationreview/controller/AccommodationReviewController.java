package com.zerobase.travel.accommodationreview.controller;

import com.zerobase.travel.accommodationreview.dto.AccommodationReviewDto;
import com.zerobase.travel.accommodationreview.dto.PostsRequestDto;
import com.zerobase.travel.accommodationreview.dto.UpdateRequestDto;
import com.zerobase.travel.accommodationreview.service.AccommodationReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;


@RequiredArgsConstructor
@RequestMapping
@RestController
public class AccommodationReviewController {

    private final AccommodationReviewService accommodationReviewService;

    @GetMapping("/accommodation/{roomId}/review")
    public ResponseEntity<List<AccommodationReviewDto>> accommodationReviewList(@PathVariable("roomId") int id){
        //todo: 숙소 리뷰 조회
        List<AccommodationReviewDto> accommodationReviewDto=accommodationReviewService.reviewList(id);

        try{
            return new ResponseEntity<>(accommodationReviewDto, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(accommodationReviewDto,HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/accommodation/{roomId}/review")
    public ResponseEntity accommodationReviewPost(@PathVariable("roomId") int rmid, @RequestBody PostsRequestDto postsRequestDto) {

        if(postsRequestDto.getGrade()<=0||postsRequestDto.getGrade()>5){
            return new ResponseEntity("별점을 다시 입력해주세요",HttpStatus.BAD_REQUEST);
        }

        //todo: 숙소 리뷰 등록
        PostsRequestDto requestDto = PostsRequestDto.builder()
                .roomId(rmid)
                .userId(1)  //todo: 사용자 id 가져와야함
                .content(postsRequestDto.getContent())
                .writer(postsRequestDto.getWriter())
                .grade(postsRequestDto.getGrade())
                .build();

        try {
            accommodationReviewService.reviewSave(rmid, requestDto);
            return new ResponseEntity("리뷰 등록에 완료되었습니다.", HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity("리뷰 등록에 실패하였습니다. 리뷰 내용, 작성자를 모두 입력해주세요.", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/accommodation/{roomId}/review/{reviewId}")
    public ResponseEntity accommodationReviewUpdate(@PathVariable("roomId") int rmId, @PathVariable("reviewId") int rvId, @RequestBody UpdateRequestDto updateRequestDto) {

        //todo: 숙소 리뷰 사용자인지 확인

        //todo: 숙소 리뷰 수정
        try {
            if(accommodationReviewService.reviewUpdate(rvId, updateRequestDto)) {
                return new ResponseEntity("리뷰가 수정되었습니다.", HttpStatus.OK);
            }else{
                return new ResponseEntity("리뷰가 존재하지 않습니다. 다시 확인해주세요",HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity("리뷰 수정에 실패하였습니다. 다시 시도해주세요.", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/accommodation/{roomId}/review/{reviewId}")
    public ResponseEntity accommodationReviewDelete(@PathVariable("roomId") int rmId, @PathVariable("reviewId") int rvId)
    {

        //todo: 숙소 리뷰 사용자인지 확인


        //todo: 숙소 리뷰 삭제
        try{
            if(accommodationReviewService.reviewDelete(rvId))
            {
                return new ResponseEntity("리뷰가 삭제되었습니다.",HttpStatus.OK);
            }else{
                return new ResponseEntity("리뷰가 존재하지 않습니다. 다시 확인해주세요",HttpStatus.NOT_FOUND);
            }

        }catch(Exception e){
            return new ResponseEntity<>("리뷰 삭제에 실패하였습니다.",HttpStatus.NOT_FOUND);
        }
    }


}
