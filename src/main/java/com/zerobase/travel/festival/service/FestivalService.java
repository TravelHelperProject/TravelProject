package com.zerobase.travel.festival.service;

import com.zerobase.travel.festival.dto.FestivalDTO;

import java.util.List;

public interface FestivalService {
    List<FestivalDTO> list();
    List<FestivalDTO> search(String keyword);
    FestivalDTO get(long festivalId);
}
