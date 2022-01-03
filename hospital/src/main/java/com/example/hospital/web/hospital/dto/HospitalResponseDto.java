package com.example.hospital.web.hospital.dto;

import com.example.hospital.web.hospital.domain.Hospital;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class HospitalResponseDto {

    private String name;
    private String address;

    public static HospitalResponseDto toResponseDto(Hospital hospital){
        HospitalResponseDto build = HospitalResponseDto.builder()
                .name(hospital.getName())
                .address(hospital.getAddress())
                .build();
        return build;
    }


}
