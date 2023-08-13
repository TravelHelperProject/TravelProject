package com.zerobase.travel.festival_review.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FestivalReviewDTO {

    private long reviewId;
    private long festivalId;
    private long userId;
    private String content;
    private String writer;
    private int grade;
    private Date createdDate;

}
