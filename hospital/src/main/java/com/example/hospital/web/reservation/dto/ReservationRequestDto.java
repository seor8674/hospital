package com.example.hospital.web.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ReservationRequestDto {
    private LocalDateTime time;
    private String userName;
    private String doctorname;
}
