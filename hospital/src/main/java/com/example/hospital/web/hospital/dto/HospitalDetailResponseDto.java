package com.example.hospital.web.hospital.dto;

import com.example.hospital.web.doctor.dto.DoctorResponseDto;
import com.example.hospital.web.hospital.domain.Hospital;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class HospitalDetailResponseDto {

    private String name;

    private String address;

    List<DoctorResponseDto> doctorResponseDtos;


    public static HospitalDetailResponseDto toResponseDto(Hospital hospital){
        return HospitalDetailResponseDto.builder()
                .name(hospital.getName())
                .address(hospital.getAddress())
                .doctorResponseDtos(hospital.getDoctors().stream().map(DoctorResponseDto::toResponseDto).collect(Collectors.toList()))
                .build();
    }
}
