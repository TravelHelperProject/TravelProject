package com.zerobase.travel.accommodationreview.dto;

import lombok.*;


import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PostsRequestDto {

    private int reviewId;
    private int roomId;
    private int userId;

    @NotEmpty(message = "리뷰 내용을 입력해 주세요.")
    private String content;
    @NotEmpty(message = "리뷰 작성자를 입력해 주세요.")
    private String writer;

    @NotEmpty(message = "별점을 입력해주세요(1~5)")
    private int grade;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
