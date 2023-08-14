package com.zerobase.travel.festival.controller;

import com.zerobase.travel.board.dto.Criteria;
import com.zerobase.travel.festival.dto.FestivalDTO;
import com.zerobase.travel.festival.service.FestivalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class FestivalController {

    private final FestivalService service;

    @GetMapping("/festival/list")  // 축제 리스트
    public ResponseEntity<List<FestivalDTO>> festivalList() {
        List<FestivalDTO> list = service.list();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/festival/search") // 축제 키워드 검색
    public ResponseEntity<List<FestivalDTO>> festivalSearch(@RequestBody Criteria cri) {

        List<FestivalDTO> festivalDTO = service.search(cri);
        return ResponseEntity.status(HttpStatus.OK).body(festivalDTO);
    }
    @GetMapping("/festival/{festivalId}") // 축제 상세조회
    public ResponseEntity<FestivalDTO> festivalGet(@PathVariable long festivalId) {
       FestivalDTO festivalDTO = service.get(festivalId);
        return ResponseEntity.status(HttpStatus.OK).body(festivalDTO);
    }
}
