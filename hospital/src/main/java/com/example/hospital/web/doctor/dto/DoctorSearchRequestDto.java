package com.example.hospital.web.doctor.dto;

import com.example.hospital.web.doctor.domain.Spetialization;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DoctorSearchRequestDto {
    private String hospitalname;
    private Spetialization spetialization;
    private int page;
}
