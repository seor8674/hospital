package com.example.hospital.web.user.dto;

import com.example.hospital.web.reservation.domain.Reservation;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class FindReservationResponseDto {

    private LocalDateTime time;
    private String hostpitalname;
    private String doctorname;

    public static FindReservationResponseDto toResponseDto(Reservation reservation){
        return FindReservationResponseDto.builder().
                time(reservation.getReservationDate())
                .doctorname(reservation.getDoctor().getName())
                .hostpitalname(reservation.getDoctor().getHospital().getName()).build();
    }

}
