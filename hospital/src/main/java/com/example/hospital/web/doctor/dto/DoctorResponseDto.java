package com.example.hospital.web.doctor.dto;

import com.example.hospital.web.doctor.domain.Doctor;
import com.example.hospital.web.doctor.domain.Spetialization;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ToString
public class DoctorResponseDto {

    private String name;
    private Spetialization spetialization;
    private int period;
    private String Hospitalname;

    public static DoctorResponseDto toResponseDto(Doctor doctor){
        return DoctorResponseDto.builder()
                .name(doctor.getName())
                .spetialization(doctor.getSpetialization())
                .period(doctor.getPeriod())
                .Hospitalname(doctor.getHospital().getName())
                .build();
    }
}
