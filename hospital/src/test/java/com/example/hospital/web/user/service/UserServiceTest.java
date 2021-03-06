package com.example.hospital.web.user.service;

import com.example.hospital.config.util.SecurityUtil;
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
import com.example.hospital.web.reservation.dto.ReservationResponseDto;
import com.example.hospital.web.reservation.service.ReservationService;
import com.example.hospital.web.user.domain.User;
import com.example.hospital.web.user.domain.UserRepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@WithMockUser(username = "test1")
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

    @Autowired
    private ReservationService reservationService;


    @BeforeEach
    void init(){
        userRepository.save(new User("test1","1234",32,"?????????",true));
    }


    @Test
    @DisplayName("????????? ???????????? doctor?????? user?????? ?????? ?????????????????????")
    public void reservationRegistTest(){
        Long aLong = reservationService.makeReservation(new ReservationRequestDto(LocalDateTime.now(),  "?????????"));
        Reservation reservation = reservationRepository.findByIdfetchUserandDoctor(aLong);
        assertThat(reservation.getUser().getUserName()).isEqualTo("seor8674");
        assertThat(reservation.getDoctor().getName()).isEqualTo("?????????");
        assertThat(userRepository.findByUserNamefetchReservation("seor8674").getReservationList().size()).isEqualTo(1);
        assertThat(doctorRepository.findByDoctorNamefetchReservation("?????????").getReservationList().size()).isEqualTo(1);
    }

    @Test()
    @DisplayName("?????? ???????????? ?????????????????? ????????? ????????????.")
    public void reservationErrorTest(){
        Long ????????? = reservationService.makeReservation(new ReservationRequestDto(LocalDateTime.now(), "?????????"));
        int k=0;
        try {
            reservationService.makeReservation(new ReservationRequestDto(LocalDateTime.now(), "?????????"));
        }catch (GlobalApiException e){
            k=1;
        }
        assertThat(k).isEqualTo(1);
        try {
            reservationService.makeReservation(new ReservationRequestDto(LocalDateTime.now().plusHours(2), "?????????"));
        }catch (GlobalApiException e){
            k=2;
        }
        assertThat(k).isEqualTo(1);
    }

    @Test
    @DisplayName("????????? ????????? ??????????????? ??? ??? ??????.")
    public void findReservationTest(){
        reservationService.makeReservation(new ReservationRequestDto(LocalDateTime.now(), "?????????"));
        reservationService.makeReservation(new ReservationRequestDto(LocalDateTime.now(), "?????????"));
        reservationService.makeReservation(new ReservationRequestDto(LocalDateTime.now(), "?????????"));
        Page<ReservationResponseDto> reservation = userService.findReservation(PageRequest.of(0, 2));

    }







}