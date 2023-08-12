package com.zerobase.travel.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EmailDTO {
    private String address;
    private String title;
    private String content;
}
