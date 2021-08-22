package com.example.hospital.web.doctor.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface DoctorRepositoryCustom {

    Doctor findByDoctorNamefetchReservation(String name);

    Page<Doctor> findByDoctorBySpetialization(Pageable pageable,Spetialization spetialization);

    Page<Doctor> findByDoctorByHopitalname(Pageable pageable,String Hospitalname);

    Page<Doctor> findByDoctorByHopitalnameandSpetialization(Pageable pageable,String Hospitalname,Spetialization spetialization);


}
