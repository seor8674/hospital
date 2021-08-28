package com.example.hospital.web.reservation.service;

import com.example.hospital.config.util.SecurityUtil;
import com.example.hospital.web.doctor.domain.Doctor;
import com.example.hospital.web.doctor.domain.DoctorRepository;
import com.example.hospital.web.exception.ErrorCode;
import com.example.hospital.web.exception.GlobalApiException;
import com.example.hospital.web.reservation.domain.Reservation;
import com.example.hospital.web.reservation.domain.ReservationRepository;
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
    public Long makeReservation(ReservationRequestDto reservationRequestDto){
        User user = userRepository.findByuserName(SecurityUtil.getCurrentUsername()
                .orElseThrow(() -> new GlobalApiException(ErrorCode.NONE_USER))).get();
        Doctor doctor = doctorRepository.findByname(reservationRequestDto.getDoctorname())
                .orElseThrow(() -> new GlobalApiException(ErrorCode.NONE_DATA));
        if(!doctor.checkreservation(reservationRequestDto.getTime())){
            throw new GlobalApiException(ErrorCode.RESERVED_TIME);
        }
        Reservation reservation = new Reservation(reservationRequestDto.getTime(), doctor, user);
        reservationRepository.save(reservation);
        user.getReservationList().add(reservation);
        return reservation.getId();
    }

    @Transactional
    public void modifyReservation(Long id,ReservationRequestDto reservationRequestDto){
        cancleReservation(id);
        makeReservation(new ReservationRequestDto(reservationRequestDto.getTime(),reservationRequestDto.getDoctorname()));
    }

    @Transactional
    public void Reservationcomplete(){
        reservationRepository.completeReservation();
    }


}
