package com.zerobase.travel.reservation.controller;

import com.zerobase.travel.reservation.dto.PostReservationDto;
import com.zerobase.travel.reservation.service.ReservationService;
import com.zerobase.travel.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping
@Slf4j
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/accommodation/{roomId}/reservation")
    public ResponseEntity accommodationReservation(
            @PathVariable("roomId") int roomId,
            @RequestBody PostReservationDto postReservationDto,
            @AuthenticationPrincipal UserDTO user){

        //todo: 사용자 로그인 되어 있는지 확인
        if (user == null) {
            return new ResponseEntity("로그인을 해주세요.", HttpStatus.FORBIDDEN);
        }

        //todo: 숙소 예약 기능
        try{
            reservationService.reservation(roomId,user.getUserId(),postReservationDto);
            return new ResponseEntity("숙소가 예약되었습니다. 마이페이지를 확인해주세요.", HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity("숙소예약에 실패하였습니다. 다시 시도해주세요.",HttpStatus.BAD_REQUEST);
        }
    }

}
