package com.example.hospital.web.controller;

import com.example.hospital.web.doctor.dto.DoctorResponseDto;
import com.example.hospital.web.doctor.dto.DoctorSearchRequestDto;
import com.example.hospital.web.doctor.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @GetMapping("/search")
    public Page<DoctorResponseDto> searchDoctor(DoctorSearchRequestDto doctorSearchRequestDto){
       return doctorService.searchDoctor(doctorSearchRequestDto);
    }
}
