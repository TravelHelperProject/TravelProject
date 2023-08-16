package com.zerobase.travel.reservation.service;

import com.zerobase.travel.reservation.dto.PostReservationDto;
import com.zerobase.travel.reservation.mapper.ReservationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService{

    private final ReservationMapper reservationMapper;

    @Override
    public void reservation(int roomId,int userId,PostReservationDto postReservationDto){
        reservationMapper.reservationSave(roomId,userId,postReservationDto);
    }
}
