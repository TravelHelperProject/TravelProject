package com.zerobase.travel.festival.mapper;

import com.zerobase.travel.board.dto.Criteria;
import com.zerobase.travel.festival.dto.FestivalDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FestivalMapper {

    List<FestivalDTO> list();
    List<FestivalDTO> search(Criteria cri);
    FestivalDTO get(long festivalId);

}
