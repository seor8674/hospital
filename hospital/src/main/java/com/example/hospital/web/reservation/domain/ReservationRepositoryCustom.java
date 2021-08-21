package com.example.hospital.web.reservation.domain;

import com.example.hospital.web.doctor.domain.Doctor;

public interface ReservationRepositoryCustom {


    Reservation findByIdfetchUserandDoctor(Long id);
}
