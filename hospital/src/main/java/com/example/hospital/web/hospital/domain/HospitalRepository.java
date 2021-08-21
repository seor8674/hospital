package com.example.hospital.web.hospital.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital,Long>,HospitalRepositoryCustom {

}
