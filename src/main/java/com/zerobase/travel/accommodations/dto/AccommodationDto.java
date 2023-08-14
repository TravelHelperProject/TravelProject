package com.zerobase.travel.accommodations.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class AccommodationDto {

    @JsonProperty
    private String roomName;
    @JsonProperty
    private String roomLocation;
    @JsonProperty
    private int price;
    @JsonProperty
    private int numberRooms;
    @JsonProperty
    private int numberPeople;
    @JsonProperty
    private String checkin;
    @JsonProperty
    private String checkout;
    @JsonProperty
    private String information;
}
