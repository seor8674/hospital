package com.example.hospital.web.doctor.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor,Long>,DoctorRepositoryCustom{

    public Optional<Doctor> findByname(String name);
}
