package com.example.hospital.web.hospital.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HospitalRepositoryImplTest {


    @Autowired
    HospitalRepository hospitalRepository;

    @BeforeEach
    void init(){
        hospitalRepository.save(new Hospital("세브란스병원","서울특별시 서대문구 연세로 50-1"));
        hospitalRepository.save(new Hospital("구로고대병원","서울특별시 구로구 구로동로 148"));
        hospitalRepository.save(new Hospital("이대목동병원","서울특별시 양천구 안양천로 1071"));
        hospitalRepository.save(new Hospital("구로성심병원","서울특별시 구로구 경인로 427"));
        hospitalRepository.save(new Hospital("부천성모병원","경기도 부천시 소사로 327"));
        hospitalRepository.save(new Hospital("인하대병원","인천광역시 중구 인항로 27"));
        hospitalRepository.save(new Hospital("건국대병원","서울특별시 광진구 능동로 120-1"));
        hospitalRepository.save(new Hospital("서울대병원","서울특별시 종로구 대학로 101"));
        hospitalRepository.save(new Hospital("부산대병원","부산광역시 서구 구덕로 179"));
        hospitalRepository.save(new Hospital("고려대병원","서울특별시 성북구 고려대로 73"));
    }

    @Test
    @Transactional
    @DisplayName("모든 병원 조회")
    public void findAllByPageTest(){
        PageRequest pageRequest = PageRequest.of(0, 3);
        Page<Hospital> allHospital = hospitalRepository.findAllHospital(pageRequest);
        List<Hospital> content = allHospital.getContent();
        long totalElements = allHospital.getTotalElements();
        int totalPages = allHospital.getTotalPages();
        assertThat(content.size()).isEqualTo(3);
        assertThat(totalElements).isEqualTo(10);
        assertThat(totalPages).isEqualTo(4);
    }

    @Test
    @Transactional
    @DisplayName("병원을 이름으로 조회한다.")
    public void findBynameTest(){
        PageRequest pageRequest = PageRequest.of(0, 3);
        Page<Hospital> allHospital = hospitalRepository.findHospitalByname(pageRequest,"대병원");
        List<Hospital> content = allHospital.getContent();
        long totalElements = allHospital.getTotalElements();
        int totalPages = allHospital.getTotalPages();
        assertThat(content.size()).isEqualTo(3);
        assertThat(totalElements).isEqualTo(6);
        assertThat(totalPages).isEqualTo(2);
    }

    @Test
    @Transactional
    @DisplayName("병원을 주소로 조회한다.")
    public void findByaddressTest(){
        PageRequest pageRequest = PageRequest.of(0, 3);
        Page<Hospital> allHospital = hospitalRepository.findHospitalByaddress(pageRequest,"서울");
        List<Hospital> content = allHospital.getContent();
        long totalElements = allHospital.getTotalElements();
        int totalPages = allHospital.getTotalPages();
        assertThat(content.size()).isEqualTo(3);
        assertThat(totalElements).isEqualTo(7);
        assertThat(totalPages).isEqualTo(3);
        Page<Hospital> pu = hospitalRepository.findHospitalByaddress(pageRequest,"부산");
        List<Hospital> content1 = pu.getContent();
        long totalElements1 = pu.getTotalElements();
        int totalPages1 = pu.getTotalPages();
        assertThat(content1.size()).isEqualTo(1);
        assertThat(totalElements1).isEqualTo(1);
        assertThat(totalPages1).isEqualTo(1);


    }
}