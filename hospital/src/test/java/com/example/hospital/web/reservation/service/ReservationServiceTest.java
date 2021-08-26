package com.example.hospital.web.reservation.service;

import com.example.hospital.config.util.SecurityUtil;
import com.example.hospital.web.doctor.domain.Doctor;
import com.example.hospital.web.doctor.domain.DoctorRepository;
import com.example.hospital.web.doctor.domain.Spetialization;
import com.example.hospital.web.hospital.domain.Hospital;
import com.example.hospital.web.hospital.domain.HospitalRepository;
import com.example.hospital.web.reservation.domain.Reservation;
import com.example.hospital.web.reservation.domain.ReservationRepository;
import com.example.hospital.web.reservation.dto.ReservationRequestDto;
import com.example.hospital.web.user.domain.User;
import com.example.hospital.web.user.domain.UserRepository;
import com.example.hospital.web.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@WithMockUser(username = "seor8674")
class ReservationServiceTest {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationService reservationService;

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
    @DisplayName("예약을 취소하면 의사의 리스트 환자의 리스트에서 모두 없어진다")
    public void cancleReservationTest(){
        Long 안철수 = reservationService.makeReservation(new ReservationRequestDto(LocalDateTime.now(), "안철수"));
        Long 안철수5 = reservationService.makeReservation(new ReservationRequestDto(LocalDateTime.now().plusHours(5), "안철수"));
        Long 김영희 = reservationService.makeReservation(new ReservationRequestDto(LocalDateTime.now(), "김영희"));
        Long 박태수 = reservationService.makeReservation(new ReservationRequestDto(LocalDateTime.now(), "박태수"));
        String s = SecurityUtil.getCurrentUsername().get();
        User user = userRepository.findByUserNamefetchReservation(s);
        Doctor 안철수1 = doctorRepository.findByDoctorNamefetchReservation("안철수");
        List<Reservation> all = reservationRepository.findAll();
        assertThat(user.getReservationList().size()).isEqualTo(4);
        assertThat(all.size()).isEqualTo(4);
        assertThat(안철수1.getReservationList().size()).isEqualTo(2);
        reservationService.cancleReservation(안철수);
        User user1 = userRepository.findByUserNamefetchReservation(s);
        Doctor 안철수2 = doctorRepository.findByDoctorNamefetchReservation("안철수");
        List<Reservation> all1 = reservationRepository.findAll();
        assertThat(user1.getReservationList().size()).isEqualTo(3);
        assertThat(all1.size()).isEqualTo(3);
        assertThat(안철수2.getReservationList().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("예약 변경 테스트")
    public void modifyReservationTest(){
        Long 안철수 = reservationService.makeReservation(new ReservationRequestDto(LocalDateTime.now(), "안철수"));
        Long 안철수5 = reservationService.makeReservation(new ReservationRequestDto(LocalDateTime.now().plusHours(5), "안철수"));
        Long 김영희 = reservationService.makeReservation(new ReservationRequestDto(LocalDateTime.now(), "김영희"));
        Long 박태수 = reservationService.makeReservation(new ReservationRequestDto(LocalDateTime.now(), "박태수"));
        String s = SecurityUtil.getCurrentUsername().get();
        User user = userRepository.findByUserNamefetchReservation(s);
        Doctor 안철수1 = doctorRepository.findByDoctorNamefetchReservation("안철수");
        List<Reservation> all = reservationRepository.findAll();
        Doctor 김영희1 = doctorRepository.findByDoctorNamefetchReservation("김영희");
        assertThat(user.getReservationList().size()).isEqualTo(4);
        assertThat(all.size()).isEqualTo(4);
        assertThat(안철수1.getReservationList().size()).isEqualTo(2);
        assertThat(김영희1.getReservationList().size()).isEqualTo(1);
        reservationService.modifyReservation(안철수,new ReservationRequestDto(LocalDateTime.now().plusHours(2),"김영희"));
        User user1 = userRepository.findByUserNamefetchReservation(s);
        Doctor 안철수2 = doctorRepository.findByDoctorNamefetchReservation("안철수");
        Doctor 김영희2 = doctorRepository.findByDoctorNamefetchReservation("김영희");
        List<Reservation> all1 = reservationRepository.findAll();
        assertThat(user1.getReservationList().size()).isEqualTo(4);
        assertThat(all1.size()).isEqualTo(4);
        assertThat(안철수2.getReservationList().size()).isEqualTo(1);
        assertThat(김영희2.getReservationList().size()).isEqualTo(2);
    }


}