package com.zerobase.travel.accommodationreview.service;

import com.zerobase.travel.accommodationreview.dto.AccommodationReviewDto;
import com.zerobase.travel.accommodationreview.dto.PostsRequestDto;
import com.zerobase.travel.accommodationreview.dto.UpdateRequestDto;


import java.util.List;

public interface AccommodationReviewService {
    public List<AccommodationReviewDto> reviewList(int roomId);
    public void reviewSave(int roomId, PostsRequestDto postsRequestDto);
    public boolean reviewUpdate(int reviewId, UpdateRequestDto updateRequestDto);
    public boolean reviewDelete(int reviewId);
    public AccommodationReviewDto reviewFindById(int reviewId);
}
