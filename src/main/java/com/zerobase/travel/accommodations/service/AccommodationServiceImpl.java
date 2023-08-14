package com.zerobase.travel.accommodations.service;

import com.zerobase.travel.accommodations.dto.AccommodationDto;
import com.zerobase.travel.accommodations.dto.KeywordDto;
import com.zerobase.travel.accommodations.mapper.AccommodationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccommodationServiceImpl implements AccommodationService{

    private final AccommodationMapper accommodationMapper;

    @Override
    public List<AccommodationDto> list(){
        return accommodationMapper.accommodationList();
    }

    @Override
    public List<AccommodationDto> searchList(KeywordDto keyword){
//        String result = "%" + keyword + "%";


        return accommodationMapper.accommodationSearch(keyword);
    }

}