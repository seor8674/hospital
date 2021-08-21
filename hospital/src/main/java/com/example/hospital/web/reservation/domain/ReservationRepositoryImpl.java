package com.example.hospital.web.reservation.domain;

import com.example.hospital.web.doctor.domain.Doctor;
import com.example.hospital.web.doctor.domain.QDoctor;
import com.example.hospital.web.user.domain.QUser;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

import static com.example.hospital.web.doctor.domain.QDoctor.*;
import static com.example.hospital.web.reservation.domain.QReservation.reservation;
import static com.example.hospital.web.user.domain.QUser.*;

public class ReservationRepositoryImpl implements ReservationRepositoryCustom {



    @Autowired
    EntityManager entityManager;

    private final JPAQueryFactory jpaQueryFactory;

    public ReservationRepositoryImpl(EntityManager entityManager) {
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Reservation findByIdfetchUserandDoctor(Long id) {
        Reservation reservation = jpaQueryFactory.selectFrom(QReservation.reservation)
                .join(QReservation.reservation.user, user).fetchJoin()
                .join(QReservation.reservation.doctor, doctor).fetchJoin()
                .where(QReservation.reservation.id.eq(id)).fetchOne();

        return reservation;
    }
}
