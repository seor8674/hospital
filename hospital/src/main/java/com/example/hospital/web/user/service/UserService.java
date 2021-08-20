package com.example.hospital.web.user.service;

import com.example.hospital.config.auth.Authority;
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
import com.example.hospital.web.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ReservationRepository reservationRepository;
    private final DoctorRepository doctorRepository;


    @Transactional
    public User signup(UserDto userDto) {
        if (userRepository.findOneWithAuthoritiesByuserName(userDto.getUserName()).orElse(null) != null) {
            throw new GlobalApiException(ErrorCode.DUPLICATE_USER);
        }

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        User user = User.builder()
                .userName(userDto.getUserName())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .name(userDto.getName())
                .age(userDto.getAge())
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthorities(String username) {
        return userRepository.findOneWithAuthoritiesByuserName(username);
    }

    @Transactional(readOnly = true)
    public Optional<User> getMyUserWithAuthorities() {
        return SecurityUtil.getCurrentUsername().flatMap(userRepository::findOneWithAuthoritiesByuserName);
    }

    @Transactional
    public Long makeReservation(ReservationRequestDto reservationRequestDto){
        User user = userRepository.findByuserName(reservationRequestDto.getUserName())
                .orElseThrow(() -> new GlobalApiException(ErrorCode.NONE_USER));
        Doctor doctor = doctorRepository.findByname(reservationRequestDto.getDoctorname())
                .orElseThrow(() -> new GlobalApiException(ErrorCode.NONE_DATA));
        Reservation reservation = new Reservation(reservationRequestDto.getTime(), doctor, user);
        reservationRepository.save(reservation);
        user.getReservationList().add(reservation);
        return reservation.getId();

    }
}
