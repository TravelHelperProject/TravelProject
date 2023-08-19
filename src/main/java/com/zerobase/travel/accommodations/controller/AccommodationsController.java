package com.zerobase.travel.accommodations.controller;


import com.zerobase.travel.accommodations.dto.AccommodationDto;
import com.zerobase.travel.accommodations.dto.KeywordDto;
import com.zerobase.travel.accommodations.service.AccommodationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/accommodation/*")
@RestController
public class AccommodationsController {

    private final AccommodationService accommodationService;


    @GetMapping("/list")
    public ResponseEntity<List<AccommodationDto>> accommodationList() {
        List<AccommodationDto> accommodationDto = accommodationService.list();

        //todo: 숙소 전체조회 기능
        try {
            return new ResponseEntity<>(accommodationDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(accommodationDto, HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/search")
    public ResponseEntity<List<AccommodationDto>> accommodationSearch(@RequestBody KeywordDto keyword) {
        List<AccommodationDto> accommodationDto = accommodationService.searchList(keyword);

        //todo: 숙소 검색 기능
        try {
            return new ResponseEntity<>(accommodationDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(accommodationDto, HttpStatus.NOT_FOUND);
        }
    }

}

