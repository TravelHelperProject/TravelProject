package com.zerobase.travel.festival_review.mapper;

import com.zerobase.travel.festival_review.dto.FestivalReviewDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FestivalReviewMapper {
    List<FestivalReviewDTO> list(long festivalId);
    int insert(FestivalReviewDTO festivalReviewDTO);
    int modify(FestivalReviewDTO festivalReviewDTO);
    int delete(long festivalId, long reviewId);
    FestivalReviewDTO get(long reviewId);
}
