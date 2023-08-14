package com.zerobase.travel.accommodations.domain;

import com.fasterxml.jackson.annotation.JsonTypeId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.sql.Time;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;

@NoArgsConstructor
@Setter
@Getter
public class Accommodation {


    private int id;

    private String roomName;
    private String roomLocation;
    private int price;
    private int numberRooms;
    private int numberPeople;
    private String checkin;
    private String checkout;
    private String information;
    private LocalDateTime createAt;
    private LocalDateTime updatedAt;
    private float latitude;
    private float longitude;

    //생성자


}