package com.example.hospital.web.hospital.service;

import com.example.hospital.web.doctor.domain.DoctorRepository;
import com.example.hospital.web.hospital.domain.Hospital;
import com.example.hospital.web.hospital.domain.HospitalRepository;
import com.example.hospital.web.hospital.dto.HospitalDetailResponseDto;
import com.example.hospital.web.hospital.dto.HospitalResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HospitalService {

    @Autowired
    HospitalRepository hospitalRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Transactional(readOnly = true)
    public List<HospitalResponseDto> getAllHospital(Pageable pageable){
        Page<Hospital> allHospital = hospitalRepository.findAllHospital(pageable);
        return allHospital.getContent().stream()
                .map(HospitalResponseDto::toResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<HospitalResponseDto> getHospitalByName(Pageable pageable,String name){
        Page<Hospital> hospitalByname = hospitalRepository.findHospitalByname(pageable, name);
        return hospitalByname.getContent().stream()
                .map(HospitalResponseDto::toResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<HospitalResponseDto> getHospitalByAddress(Pageable pageable,String address){
        Page<Hospital> hospitalByaddress = hospitalRepository.findHospitalByaddress(pageable, address);
        return hospitalByaddress.getContent().stream()
                .map(HospitalResponseDto::toResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public HospitalDetailResponseDto getHospitalDetail(String hospitalname){
        Hospital hospital = hospitalRepository.findByName(hospitalname).get();
        return HospitalDetailResponseDto.toResponseDto(hospital);
    }


}
