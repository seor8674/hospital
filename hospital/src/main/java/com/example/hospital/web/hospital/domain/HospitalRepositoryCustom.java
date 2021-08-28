package com.example.hospital.web.hospital.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HospitalRepositoryCustom {

    Page<Hospital> findAllHospital(Pageable pageable);

    Page<Hospital> findHospitalByname(Pageable pageable,String name);

    Page<Hospital> findHospitalByaddress(Pageable pageable,String address);

    Page<Hospital> findHospitalByaddressandname(Pageable pageable,String address,String name);


}
