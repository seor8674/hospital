package com.example.hospital.web.user.domain;

import java.util.Optional;

public interface UserRepositoryCustom {

    User findByUserNamefetchReservation(String Username);
}
