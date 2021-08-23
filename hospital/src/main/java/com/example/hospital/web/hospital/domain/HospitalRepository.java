package com.example.hospital.web.hospital.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HospitalRepository extends JpaRepository<Hospital,Long>,HospitalRepositoryCustom {


    public Optional<Hospital> findByName(String name);

}
