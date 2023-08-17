package com.zerobase.travel.accommodationreview.service;

import com.zerobase.travel.accommodationreview.dto.AccommodationReviewDto;
import com.zerobase.travel.accommodationreview.dto.PostsRequestDto;
import com.zerobase.travel.accommodationreview.dto.UpdateRequestDto;
import com.zerobase.travel.accommodationreview.mapper.AccommodationReviewMapper;
import com.zerobase.travel.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;

import org.apache.ibatis.jdbc.Null;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class AccommodationReviewServiceImpl implements AccommodationReviewService{

    private final AccommodationReviewMapper accommodationReviewMapper;

    @Override
    public List<AccommodationReviewDto> reviewList(int roomId){
        //todo: 리뷰 상세 조회
        return accommodationReviewMapper.reviewList(roomId);
    }

    @Override
    public void reviewSave(int roomId, PostsRequestDto postsRequestDto){
        //todo: 리뷰 저장

        accommodationReviewMapper.reviewSave(roomId, postsRequestDto);
    }

    @Override
    public boolean reviewUpdate(int reviewId, UpdateRequestDto updateRequestDto){
        //todo: 리뷰가 존재하는지 확인
        AccommodationReviewDto accommodationReviewDto=reviewFindById(reviewId);

        if(accommodationReviewDto!=null){
            //todo: 리뷰 수정
            accommodationReviewMapper.reviewUpdate(reviewId,updateRequestDto);
        }else{
            return false;
        }
        return true;
    }

    @Override
    public boolean reviewDelete(int reviewId){
        //todo: 리뷰가 존재하는지 확인
        AccommodationReviewDto accommodationReviewDto=reviewFindById(reviewId);

        if(accommodationReviewDto!=null) {
            //todo: 리뷰 삭제
            accommodationReviewMapper.reviewDelete(reviewId);
        }else{
            return false;
        }
        return true;
    }

    @Override
    public AccommodationReviewDto reviewFindById(int reviewId){
        return accommodationReviewMapper.reviewFindById(reviewId);
    }

    public boolean reviewUserCheck(int reviewId, UserDTO user){
        return user.getUserId() == reviewFindById(reviewId).getUserId();
    }



}
