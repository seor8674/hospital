package com.example.hospital.web.reservation.service;

import com.example.hospital.config.util.SecurityUtil;
import com.example.hospital.web.doctor.domain.Doctor;
import com.example.hospital.web.doctor.domain.DoctorRepository;
import com.example.hospital.web.exception.ErrorCode;
import com.example.hospital.web.exception.GlobalApiException;
import com.example.hospital.web.reservation.domain.Reservation;
import com.example.hospital.web.reservation.domain.ReservationRepository;
import com.example.hospital.web.reservation.dto.ReservationModifyRequestDto;
import com.example.hospital.web.reservation.dto.ReservationRequestDto;
import com.example.hospital.web.user.domain.User;
import com.example.hospital.web.user.domain.UserRepository;
import com.example.hospital.web.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    @Transactional
    public void cancleReservation(Long id){
        reservationRepository.deleteById(id);
    }

    @Transactional
    public void modifyReservation(ReservationModifyRequestDto reservationModifyRequestDto){
        cancleReservation(reservationModifyRequestDto.getId());
        userService.makeReservation(new ReservationRequestDto(reservationModifyRequestDto.getTime(),reservationModifyRequestDto.getDoctorname()));
    }


}
