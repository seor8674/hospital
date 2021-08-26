package com.example.hospital.web.controller;

import com.example.hospital.web.reservation.dto.ReservationRequestDto;
import com.example.hospital.web.reservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @PostMapping("/makeReservation")
    public void makeReservation(@Valid @RequestBody ReservationRequestDto reservationRequestDto){
        reservationService.makeReservation(reservationRequestDto);
    }

    @DeleteMapping("/{reservationid}")
    public void cancleReservation(@PathVariable(value = "reservationid") Long id){
        reservationService.cancleReservation(id);
    }

    @PutMapping("/{reservationid}")
    public void modifyReservation(@PathVariable(value = "reservationid") Long id,@Valid @RequestBody ReservationRequestDto reservationRequestDto){
        reservationService.modifyReservation(id,reservationRequestDto);
    }

}
