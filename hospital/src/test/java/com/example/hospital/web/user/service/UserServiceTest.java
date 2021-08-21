package com.example.hospital.web.user.service;

import com.example.hospital.web.doctor.domain.Doctor;
import com.example.hospital.web.doctor.domain.DoctorRepository;
import com.example.hospital.web.doctor.domain.Spetialization;
import com.example.hospital.web.exception.ErrorCode;
import com.example.hospital.web.exception.GlobalApiException;
import com.example.hospital.web.hospital.domain.Hospital;
import com.example.hospital.web.hospital.domain.HospitalRepository;
import com.example.hospital.web.reservation.domain.Reservation;
import com.example.hospital.web.reservation.domain.ReservationRepository;
import com.example.hospital.web.reservation.dto.ReservationRequestDto;
import com.example.hospital.web.user.domain.User;
import com.example.hospital.web.user.domain.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {


    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserService userService;


    @BeforeEach
    void init(){
        userRepository.save(new User("seor8674","1234",32,"이환준",true));
        userRepository.save(new User("unse7476","1234",17,"김영수",true));
        Hospital hospital = new Hospital("강남병원", "서울");
        hospitalRepository.save(hospital);
        Hospital hospital1 = new Hospital("분당병원", "경기");
        hospitalRepository.save(hospital1);
        doctorRepository.save(new Doctor("안철수", Spetialization.internal,8,hospital));
        doctorRepository.save(new Doctor("김영희", Spetialization.Dermatology,6,hospital));
        doctorRepository.save(new Doctor("박수찬", Spetialization.Neurosurgery,13,hospital));
        doctorRepository.save(new Doctor("안민준", Spetialization.psychiatry,5,hospital));
        doctorRepository.save(new Doctor("신철민", Spetialization.Surgical,9,hospital));
        doctorRepository.save(new Doctor("박태수", Spetialization.plastic_surgeon,15,hospital));
    }

    @Test
    @DisplayName("예약을 등록함면 doctor객체 user객체 모두 등록되어야한다")
    public void reservationRegistTest(){
        Long aLong = userService.makeReservation(new ReservationRequestDto(LocalDateTime.now(), "seor8674", "안철수"));
        Reservation reservation = reservationRepository.findByIdfetchUserandDoctor(aLong);
        assertThat(reservation.getUser().getUserName()).isEqualTo("seor8674");
        assertThat(reservation.getDoctor().getName()).isEqualTo("안철수");
        assertThat(userRepository.findByUserNamefetchReservation("seor8674").getReservationList().size()).isEqualTo(1);
        assertThat(doctorRepository.findByDoctorNamefetchReservation("안철수").getReservationList().size()).isEqualTo(1);

    }

}