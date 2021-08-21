package com.example.hospital.web.doctor.domain;

import com.example.hospital.web.reservation.domain.QReservation;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

import static com.example.hospital.web.doctor.domain.QDoctor.*;
import static com.example.hospital.web.reservation.domain.QReservation.*;

public class DoctorRepositoryImpl implements DoctorRepositoryCustom{



    @Autowired
    EntityManager entityManager;

    private final JPAQueryFactory jpaQueryFactory;

    public DoctorRepositoryImpl(EntityManager entityManager) {
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Doctor findByDoctorNamefetchReservation(String name) {
        Doctor doctor = jpaQueryFactory.selectFrom(QDoctor.doctor)
                .join(QDoctor.doctor.reservationList, reservation).fetchJoin()
                .where(QDoctor.doctor.name.eq(name)).fetchOne();
        return doctor;
    }
}
