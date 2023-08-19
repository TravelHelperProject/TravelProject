package com.zerobase.travel.reservation.service;

import com.zerobase.travel.reservation.dto.PostReservationDto;

public interface ReservationService {
    public void reservation(int roomId, int userId, PostReservationDto postReservationDto);

}
