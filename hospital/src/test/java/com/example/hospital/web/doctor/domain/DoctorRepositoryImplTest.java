package com.example.hospital.web.doctor.domain;

import com.example.hospital.web.hospital.domain.Hospital;
import com.example.hospital.web.hospital.domain.HospitalRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
class DoctorRepositoryImplTest {

    @Autowired
    HospitalRepository hospitalRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @BeforeEach
    void init(){
        Hospital 인하대병원 = new Hospital("인하대병원", "인천광역시 중구 인항로 27");
        Hospital 건국대병원 = new Hospital("건국대병원", "서울특별시 광진구 능동로 120-1");
        Hospital 서울대병원 = new Hospital("서울대병원", "서울특별시 종로구 대학로 101");
        Hospital 부산대병원 = new Hospital("부산대병원", "부산광역시 서구 구덕로 179");
        Hospital 고려대병원 = new Hospital("고려대병원", "서울특별시 성북구 고려대로 73");
        hospitalRepository.save(인하대병원);
        hospitalRepository.save(건국대병원);
        hospitalRepository.save(서울대병원);
        hospitalRepository.save(부산대병원);
        hospitalRepository.save(고려대병원);
        doctorRepository.save(new Doctor("김영수",Spetialization.internal,5,인하대병원));
        doctorRepository.save(new Doctor("김철수",Spetialization.plastic_surgeon,3,인하대병원));
        doctorRepository.save(new Doctor("김효수",Spetialization.Surgical,4,인하대병원));
        doctorRepository.save(new Doctor("김희수",Spetialization.Neurosurgery,8,인하대병원));
        doctorRepository.save(new Doctor("김민수",Spetialization.Dermatology,7,인하대병원));
        doctorRepository.save(new Doctor("김정수",Spetialization.psychiatry,13,인하대병원));
        doctorRepository.save(new Doctor("김휘수",Spetialization.Dentistry,3,인하대병원));

        doctorRepository.save(new Doctor("박영수",Spetialization.internal,2,건국대병원));
        doctorRepository.save(new Doctor("박철수",Spetialization.plastic_surgeon,6,건국대병원));
        doctorRepository.save(new Doctor("박효수",Spetialization.Surgical,4,건국대병원));
        doctorRepository.save(new Doctor("박희수",Spetialization.Neurosurgery,7,건국대병원));
        doctorRepository.save(new Doctor("박민수",Spetialization.Dermatology,14,건국대병원));
        doctorRepository.save(new Doctor("박정수",Spetialization.psychiatry,10,건국대병원));
        doctorRepository.save(new Doctor("박휘수",Spetialization.Dentistry,2,건국대병원));

        doctorRepository.save(new Doctor("서영수",Spetialization.internal,1,서울대병원));
        doctorRepository.save(new Doctor("서철수",Spetialization.plastic_surgeon,8,서울대병원));
        doctorRepository.save(new Doctor("서효수",Spetialization.Surgical,14,서울대병원));
        doctorRepository.save(new Doctor("서희수",Spetialization.Neurosurgery,7,서울대병원));
        doctorRepository.save(new Doctor("서민수",Spetialization.Dermatology,11,서울대병원));
        doctorRepository.save(new Doctor("서정수",Spetialization.psychiatry,13,서울대병원));

        doctorRepository.save(new Doctor("강영수",Spetialization.internal,11,부산대병원));
        doctorRepository.save(new Doctor("강철수",Spetialization.plastic_surgeon,6,부산대병원));
        doctorRepository.save(new Doctor("강효수",Spetialization.Surgical,4,부산대병원));
        doctorRepository.save(new Doctor("강희수",Spetialization.Neurosurgery,4,부산대병원));
        doctorRepository.save(new Doctor("강민수",Spetialization.Dermatology,12,부산대병원));
        doctorRepository.save(new Doctor("강정수",Spetialization.psychiatry,15,부산대병원));
        doctorRepository.save(new Doctor("강휘수",Spetialization.Dentistry,5,부산대병원));

        doctorRepository.save(new Doctor("조영수",Spetialization.internal,11,고려대병원));
        doctorRepository.save(new Doctor("조철수",Spetialization.plastic_surgeon,8,고려대병원));
        doctorRepository.save(new Doctor("조효수",Spetialization.Surgical,4,고려대병원));
        doctorRepository.save(new Doctor("조희수",Spetialization.Neurosurgery,7,고려대병원));
        doctorRepository.save(new Doctor("조민수",Spetialization.Dermatology,11,고려대병원));
        doctorRepository.save(new Doctor("조정수",Spetialization.psychiatry,3,고려대병원));
        doctorRepository.save(new Doctor("조휘수",Spetialization.Dentistry,17,고려대병원));
    }

    @Test
    @Transactional
    @DisplayName("전공으로 의사 조회")
    public void findBySpetializationTest(){
        PageRequest pageRequest = PageRequest.of(0, 3);
        Page<Doctor> byDoctorBySpetialization = doctorRepository.findByDoctorBySpetialization(pageRequest,Spetialization.internal);
        List<Doctor> content = byDoctorBySpetialization.getContent();
        long totalElements = byDoctorBySpetialization.getTotalElements();
        int totalPages = byDoctorBySpetialization.getTotalPages();
        assertThat(content.size()).isEqualTo(3);
        assertThat(totalElements).isEqualTo(5);
        assertThat(totalPages).isEqualTo(2);
    }
    @Test
    @Transactional
    @DisplayName("병원으로 의사 조회")
    public void findByhospitalTest(){
        PageRequest pageRequest = PageRequest.of(0, 3);
        Page<Doctor> byDoctorBySpetialization = doctorRepository.findByDoctorByHopitalname(pageRequest,"인하대병원");
        List<Doctor> content = byDoctorBySpetialization.getContent();
        long totalElements = byDoctorBySpetialization.getTotalElements();
        int totalPages = byDoctorBySpetialization.getTotalPages();
        assertThat(content.size()).isEqualTo(3);
        assertThat(totalElements).isEqualTo(7);
        assertThat(totalPages).isEqualTo(3);
    }
    @Test
    @Transactional
    @DisplayName("병원과 전공으로 의사 조회")
    public void findBySpetializationandHospitalTest(){
        PageRequest pageRequest = PageRequest.of(0, 3);
        Page<Doctor> byDoctorBySpetialization = doctorRepository.findByDoctorByHopitalnameandSpetialization(pageRequest,"인하대병원",Spetialization.internal);
        List<Doctor> content = byDoctorBySpetialization.getContent();
        long totalElements = byDoctorBySpetialization.getTotalElements();
        int totalPages = byDoctorBySpetialization.getTotalPages();
        assertThat(content.size()).isEqualTo(1);
        assertThat(totalElements).isEqualTo(1);
        assertThat(totalPages).isEqualTo(1);
    }

}