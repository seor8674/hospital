package com.example.hospital.web.controller;

import com.example.hospital.web.hospital.dto.HospitalDetailResponseDto;
import com.example.hospital.web.hospital.dto.HospitalResponseDto;
import com.example.hospital.web.hospital.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/find/{page}")
    public List<HospitalResponseDto> getAllHospital(@PathVariable int page){
        return hospitalService.getAllHospital(PageRequest.of(page,10));
    }

    @GetMapping("/find/searchname/{page}/{name}")
    public List<HospitalResponseDto> getHospitalByname(@PathVariable int page,@PathVariable String name){
        return hospitalService.getHospitalByName(PageRequest.of(page,10),name);
    }

    @GetMapping("/find/searchaddress/{page}/{name}")
    public List<HospitalResponseDto> getHospitalByaddress(@PathVariable int page,@PathVariable String address){
        return hospitalService.getHospitalByAddress(PageRequest.of(page,10),address);
    }

    @GetMapping("/detail/{name}")
    public HospitalDetailResponseDto getHospitalDetails(@PathVariable String name){
        return hospitalService.getHospitalDetail(name);
    }
}
