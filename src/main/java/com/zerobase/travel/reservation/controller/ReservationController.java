package com.zerobase.travel.reservation.controller;

import com.zerobase.travel.reservation.dto.PostReservationDto;
import com.zerobase.travel.reservation.mapper.ReservationMapper;
import com.zerobase.travel.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/accommodation/{roomId}/reservation")
    public ResponseEntity accommodationReservation(@PathVariable("roomId") int rmId, @RequestBody PostReservationDto postReservationDto){

        // todo: userId 찾기(jwt에서)
        int userId=1;
        //로그인이 되었을경우
        try{
            reservationService.reservation(rmId,userId,postReservationDto);
            return new ResponseEntity("숙소가 예약되었습니다. 마이페이지를 확인해주세요.", HttpStatus.OK);
        }catch(Exception e) {
            return new ResponseEntity("숙소예약에 실패하였습니다. 다시 시도해주세요.",HttpStatus.BAD_REQUEST);
        }
        //로그인이 안되었을경우

    }

}
