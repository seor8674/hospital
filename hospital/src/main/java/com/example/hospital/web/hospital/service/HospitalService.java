package com.example.hospital.web.hospital.service;

import com.example.hospital.web.doctor.domain.DoctorRepository;
import com.example.hospital.web.exception.ErrorCode;
import com.example.hospital.web.exception.GlobalApiException;
import com.example.hospital.web.hospital.domain.Hospital;
import com.example.hospital.web.hospital.domain.HospitalRepository;
import com.example.hospital.web.hospital.dto.HospitalDetailResponseDto;
import com.example.hospital.web.hospital.dto.HospitalResponseDto;
import com.example.hospital.web.hospital.dto.HospitalSearchRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public Page<HospitalResponseDto> getHospitalByAddressandname(HospitalSearchRequestDto hospitalSearchRequestDto){
        Page<Hospital> hospitalByaddress = hospitalRepository
                .findHospitalByaddressandname(PageRequest.of(hospitalSearchRequestDto.getPage(),10), hospitalSearchRequestDto.getAddress(), hospitalSearchRequestDto.getName());
        return hospitalByaddress.map(HospitalResponseDto::toResponseDto);
    }


    @Transactional(readOnly = true)
    public HospitalDetailResponseDto getHospitalDetail(String hospitalname){
        Hospital hospital = hospitalRepository.findByName(hospitalname)
                .orElseThrow(() -> new GlobalApiException(ErrorCode.NONE_DATA));
        return HospitalDetailResponseDto.toResponseDto(hospital);
    }


}
