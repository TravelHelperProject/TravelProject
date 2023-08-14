package com.zerobase.travel.festival.service;

import com.zerobase.travel.board.dto.Criteria;
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
    public List<FestivalDTO> search(Criteria cri) {
        return mapper.search(cri);
    }

    @Override
    public FestivalDTO get(long festivalId) {
        return mapper.get(festivalId);
    }
}
