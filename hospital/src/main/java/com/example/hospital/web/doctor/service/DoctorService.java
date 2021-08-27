package com.example.hospital.web.doctor.service;

import com.example.hospital.web.doctor.domain.DoctorRepository;
import com.example.hospital.web.doctor.dto.DoctorResponseDto;
import com.example.hospital.web.doctor.dto.DoctorSearchRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    public List<DoctorResponseDto> searchDoctor(DoctorSearchRequestDto doctorSearchRequestDto){
        PageRequest of = PageRequest.of(doctorSearchRequestDto.getPage(), 10);
        List<DoctorResponseDto> collect = doctorRepository.findByDoctorByHopitalnameandSpetialization(of, doctorSearchRequestDto.getHospitalname(), doctorSearchRequestDto.getSpetialization())
                .getContent().stream().map(DoctorResponseDto::toResponseDto).collect(Collectors.toList());
        return collect;
    }
}
