package com.zerobase.travel.accommodations.controller;


import com.zerobase.travel.accommodations.dto.AccommodationDto;
import com.zerobase.travel.accommodations.dto.KeywordDto;
import com.zerobase.travel.accommodations.service.AccommodationService;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.compiler.ast.Keyword;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/accommodation/*")
@RestController
public class AccommodationsController {

//    private final AccommodationServiceImpl accommodationServiceImpl;
    private final AccommodationService accommodationService;

    @GetMapping("/list")
    public ResponseEntity<List<AccommodationDto>> accommodationList(){
        List<AccommodationDto> accommodationDto=accommodationService.list();

        try{
            return new ResponseEntity<>(accommodationDto,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(accommodationDto,HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/search")
    public ResponseEntity<List<AccommodationDto>> accommodationSearch(@RequestBody KeywordDto keyword){
        List<AccommodationDto> accommodationDto = accommodationService.searchList(keyword);

        try{
            return new ResponseEntity<>(accommodationDto,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(accommodationDto,HttpStatus.NOT_FOUND);
        }
    }

}

