package com.zerobase.travel.board.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardDTO {

    private long boardId;
    private long userId;
    private String title;
    private String writer;
    private LocalDate createdAt;
    private LocalDate updateDate;
    private String content;
    private int readCnt;


}
