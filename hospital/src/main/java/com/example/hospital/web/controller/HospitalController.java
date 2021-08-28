package com.example.hospital.web.controller;

import com.example.hospital.web.hospital.dto.HospitalDetailResponseDto;
import com.example.hospital.web.hospital.dto.HospitalResponseDto;
import com.example.hospital.web.hospital.dto.HospitalSearchRequestDto;
import com.example.hospital.web.hospital.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/hospital")
public class HospitalController {

    @Autowired
    HospitalService hospitalService;


    @GetMapping("/search")
    public Page<HospitalResponseDto> searchHospital(HospitalSearchRequestDto hospitalSearchRequestDto){
        return hospitalService.getHospitalByAddressandname(hospitalSearchRequestDto);
    }

    @GetMapping("/detail/{name}")
    public HospitalDetailResponseDto getHospitalDetails(@PathVariable String name){
        return hospitalService.getHospitalDetail(name);
    }
}
