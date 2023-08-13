package com.zerobase.travel.festival.service;

import com.zerobase.travel.festival.dto.FestivalDTO;
import com.zerobase.travel.festival.mapper.FestivalMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class FestivalServiceImpl implements FestivalService{


    private final FestivalMapper mapper;

    @Override
    public List<FestivalDTO> list() {
        return mapper.list();
    }

    @Override
    public List<FestivalDTO> search(String keyword) {
        return mapper.search(keyword);
    }

    @Override
    public FestivalDTO get(long festivalId) {
        return mapper.get(festivalId);
    }
}
