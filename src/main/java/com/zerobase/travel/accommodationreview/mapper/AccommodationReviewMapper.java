package com.zerobase.travel.accommodationreview.mapper;

import com.zerobase.travel.accommodationreview.dto.AccommodationReviewDto;
import com.zerobase.travel.accommodationreview.dto.PostsRequestDto;
import com.zerobase.travel.accommodationreview.dto.UpdateRequestDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AccommodationReviewMapper {

    public List<AccommodationReviewDto> reviewList(int roomId);
    public void reviewSave(int roomId,@Param("request") PostsRequestDto postsRequestDto);
    public void reviewUpdate(int reviewId, @Param("request") UpdateRequestDto updateRequestDto);
    public void reviewDelete(int reviewId);
    public AccommodationReviewDto reviewFindById(int reviewId);
}
