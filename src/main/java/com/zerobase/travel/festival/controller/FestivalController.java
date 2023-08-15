package com.zerobase.travel.festival.controller;

import com.zerobase.travel.board.dto.Criteria;
import com.zerobase.travel.board.exception.NotFoundException;
import com.zerobase.travel.festival.dto.FestivalDTO;
import com.zerobase.travel.festival.service.FestivalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class FestivalController {

    private final FestivalService service;

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity handlerNotFoundException(NotFoundException e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/festival/list")  // 축제 리스트
    public ResponseEntity<List<FestivalDTO>> festivalList() {

        List<FestivalDTO> list = service.list();
        if(list.isEmpty()) {
            throw new NotFoundException("조회한 축제가 없습니다.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/festival/search") // 축제 키워드 검색
    public ResponseEntity<List<FestivalDTO>> festivalSearch(@RequestBody Criteria cri) {

        List<FestivalDTO> festivalDTO = service.search(cri);
        if(festivalDTO.isEmpty()) {
            throw new NotFoundException("조회한 축제가 없습니다.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(festivalDTO);
    }
    @GetMapping("/festival/{festivalId}") // 축제 상세조회
    public ResponseEntity<FestivalDTO> festivalGet(@PathVariable long festivalId) {
       FestivalDTO festivalDTO = service.get(festivalId);
        return ResponseEntity.status(HttpStatus.OK).body(festivalDTO);
    }
}
