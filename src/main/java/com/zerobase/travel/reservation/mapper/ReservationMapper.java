package com.zerobase.travel.reservation.mapper;

import com.zerobase.travel.reservation.dto.PostReservationDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ReservationMapper {
    public void reservationSave(int roomId, int userId, @Param("request") PostReservationDto postReservationDto);
}
