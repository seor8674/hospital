package com.example.hospital.web.reservation.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation,Long>,ReservationRepositoryCustom {
}
