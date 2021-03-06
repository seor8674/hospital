package com.example.hospital.web.reservation.domain;

import com.example.hospital.web.doctor.domain.Doctor;
import com.example.hospital.web.doctor.domain.QDoctor;
import com.example.hospital.web.user.domain.QUser;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;

import java.time.LocalDateTime;
import java.util.List;

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

    @Override
    public Page<Reservation> findUserReservation(Pageable pageable,String username) {
        QueryResults<Reservation> reservationQueryResults = jpaQueryFactory.selectFrom(reservation)
                .join(reservation.user, user)
                .where(user.userName.eq(username))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        List<Reservation> results = reservationQueryResults.getResults();
        long total = reservationQueryResults.getTotal();
        return new PageImpl<>(results,pageable,total);
    }


    @Override
    public void completeReservation() {
        jpaQueryFactory.delete(reservation)
                .where(reservation.reservationDate.after(LocalDateTime.now()));
        entityManager.flush();
        entityManager.clear();
    }
}
