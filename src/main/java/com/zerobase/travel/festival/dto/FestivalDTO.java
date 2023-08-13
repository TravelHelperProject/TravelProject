package com.zerobase.travel.festival.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FestivalDTO {

    private long festivalId;
    private Date startDate;
    private Date endDate;
    private String name;
    private String phoneNumber;
    private Double latitude;
    private Double longitude;
    private String keyword;
}
