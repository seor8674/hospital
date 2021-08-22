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
import com.example.hospital.web.reservation.dto.ReservationResponseDto;
import com.example.hospital.web.user.domain.User;
import com.example.hospital.web.user.domain.UserRepository;
import com.example.hospital.web.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Transactional(readOnly = true)
    public List<ReservationResponseDto> findReservation(Pageable pageable){
        String s = SecurityUtil.getCurrentUsername().orElseThrow(()->new GlobalApiException(ErrorCode.NONE_USER));
        Page<Reservation> userReservation = reservationRepository.findUserReservation(pageable,s);
        return userReservation.getContent().stream()
                .map(ReservationResponseDto::toResponseDto)
                .collect(Collectors.toList());
    }




}
