package com.zerobase.travel.festival_review.controller;

import com.zerobase.travel.festival_review.service.FestivalReviewService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class FestivalReviewController {


    private  final FestivalReviewService service;

    @GetMapping("/")
    public ResponseEntity<?> list() {

        return  null;
    }
}
