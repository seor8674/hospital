package com.example.hospital.web.user.domain;

import com.example.hospital.web.reservation.domain.QReservation;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.Optional;

import static com.example.hospital.web.reservation.domain.QReservation.*;
import static com.example.hospital.web.user.domain.QUser.*;

public class UserRepositoryImpl implements UserRepositoryCustom{


    @Autowired
    EntityManager em;

    private final JPAQueryFactory jpaQueryFactory;

    public UserRepositoryImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    public User findByUserNamefetchReservation(String Username) {
        User user = jpaQueryFactory.selectFrom(QUser.user)
                .join(QUser.user.reservationList, reservation).fetchJoin()
                .where(QUser.user.userName.eq(Username)).fetchOne();

        return user;
    }
}
