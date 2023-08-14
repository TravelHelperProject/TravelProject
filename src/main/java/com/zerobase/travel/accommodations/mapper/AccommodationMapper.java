package com.zerobase.travel.accommodations.mapper;

import com.zerobase.travel.accommodations.dto.AccommodationDto;
import com.zerobase.travel.accommodations.dto.KeywordDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccommodationMapper {

    public List<AccommodationDto> accommodationList();

    public List<AccommodationDto> accommodationSearch(KeywordDto keyword);

}