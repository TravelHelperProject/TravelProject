package com.zerobase.travel.boardcomment.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class AddCommentDTO {

    private long replyId;
    private long boardId;
    private long userId;
    private String content;
    private LocalDate createdAt;

}
