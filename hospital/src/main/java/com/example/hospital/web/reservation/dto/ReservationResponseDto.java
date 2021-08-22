package com.example.hospital.web.reservation.dto;

import com.example.hospital.web.reservation.domain.Reservation;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ReservationResponseDto {

    private LocalDateTime time;
    private String hostpitalname;
    private String doctorname;

    public static ReservationResponseDto toResponseDto(Reservation reservation){
        return ReservationResponseDto.builder().
                time(reservation.getReservationDate())
                .doctorname(reservation.getDoctor().getName())
                .hostpitalname(reservation.getDoctor().getHospital().getName()).build();
    }

}
