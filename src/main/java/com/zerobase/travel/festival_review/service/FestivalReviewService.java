package com.zerobase.travel.festival_review.service;

import com.zerobase.travel.festival_review.dto.FestivalReviewDTO;

import java.util.List;

public interface FestivalReviewService {

    List<FestivalReviewDTO> list(long festivalId);
    boolean insert(FestivalReviewDTO festivalReviewDTO);
    boolean modify(FestivalReviewDTO festivalReviewDTO);
    boolean delete(long festivalId, long reviewId);
    FestivalReviewDTO get(long reviewId);
}
