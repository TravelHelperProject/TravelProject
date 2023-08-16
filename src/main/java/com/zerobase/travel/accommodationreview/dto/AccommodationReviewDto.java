package com.zerobase.travel.accommodationreview.dto;

import lombok.*;



import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccommodationReviewDto {


    private  int reviewId;
    private int roomId;
    private int userId;

    private String content;
    private String writer;
    private int grade;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
