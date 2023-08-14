package com.zerobase.travel.festival_review.dto;

import lombok.*;

import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FestivalReviewAddDTO {

    private long reviewId;
    private long festivalId;
    private long userId;
    private String content;
    private String writer;
    private int grade;
    private LocalDate createDate;

}
