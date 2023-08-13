package com.zerobase.travel.boardcomment.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDTO {

    private long replyId;
    private long boardId;
    private long userId;
    private String content;
    private LocalDate createdAt;

}
