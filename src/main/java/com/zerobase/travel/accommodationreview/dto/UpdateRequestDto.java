package com.zerobase.travel.accommodationreview.dto;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UpdateRequestDto {

    private String content;
    private String writer;
    private int grade;

}
