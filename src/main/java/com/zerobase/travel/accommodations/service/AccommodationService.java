package com.zerobase.travel.accommodations.service;

import com.zerobase.travel.accommodations.dto.AccommodationDto;
import com.zerobase.travel.accommodations.dto.KeywordDto;

import java.util.List;

public interface AccommodationService {
    List<AccommodationDto> list();
    List<AccommodationDto> searchList(KeywordDto keyword);

//    AccommodationDto searchList(String keyword);
}