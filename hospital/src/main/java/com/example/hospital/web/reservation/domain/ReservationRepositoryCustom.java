package com.example.hospital.web.reservation.domain;

import com.example.hospital.web.doctor.domain.Doctor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReservationRepositoryCustom {


    Reservation findByIdfetchUserandDoctor(Long id);

    Page<Reservation> findUserReservation(Pageable pageable,String username);



}
