package com.zerobase.travel.festival_review.service;

import com.zerobase.travel.festival_review.dto.FestivalReviewDTO;
import com.zerobase.travel.festival_review.mapper.FestivalReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FestivalReviewServiceImpl implements  FestivalReviewService{

    private final FestivalReviewMapper mapper;

    @Override
    public List<FestivalReviewDTO> list(long festivalId) {
        return mapper.list(festivalId);
    }

    @Override
    public boolean insert(FestivalReviewDTO festivalReviewDTO) {
        return mapper.insert(festivalReviewDTO) == 1;
    }

    @Override
    public boolean modify(FestivalReviewDTO festivalReviewDTO) {
        return mapper.modify(festivalReviewDTO) == 1;
    }

    @Override
    public boolean delete(long festivalId, long reviewId) {
        return mapper.delete(festivalId, reviewId) == 1;
    }
}
