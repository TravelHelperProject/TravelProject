package com.zerobase.travel.festival_review.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FestivalReviewModifyDTO {

    private long reviewId;
    private long festivalId;
    private long userId;
    private String content;
    private int grade;
    private LocalDate createDate;

}
