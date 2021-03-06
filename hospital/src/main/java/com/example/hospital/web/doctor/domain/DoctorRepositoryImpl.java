package com.example.hospital.web.doctor.domain;

import com.example.hospital.web.hospital.domain.QHospital;
import com.example.hospital.web.reservation.domain.QReservation;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;

import java.util.List;

import static com.example.hospital.web.doctor.domain.QDoctor.*;
import static com.example.hospital.web.hospital.domain.QHospital.*;
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

    @Override
    public Page<Doctor> findByDoctorBySpetialization(Pageable pageable,Spetialization spetialization) {
        QueryResults<Doctor> doctorQueryResults = jpaQueryFactory.selectFrom(doctor)
                .where(spetializationeq(spetialization))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        List<Doctor> results = doctorQueryResults.getResults();
        long total = doctorQueryResults.getTotal();
        return new PageImpl<>(results,pageable,total);
    }

    @Override
    public Page<Doctor> findByDoctorByHopitalname(Pageable pageable,String Hospitalname) {
        QueryResults<Doctor> doctorQueryResults = jpaQueryFactory.selectFrom(doctor)
                .join(doctor.hospital, hospital)
                .where(hospitalnamecontain(Hospitalname))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        List<Doctor> results = doctorQueryResults.getResults();
        long total = doctorQueryResults.getTotal();
        return new PageImpl<>(results,pageable,total);
    }

    @Override
    public Page<Doctor> findByDoctorByHopitalnameandSpetialization(Pageable pageable, String Hospitalname, Spetialization spetialization) {
        QueryResults<Doctor> doctorQueryResults = jpaQueryFactory.selectFrom(doctor)
                .join(doctor.hospital, hospital)
                .where(hospitalnamecontain(Hospitalname),spetializationeq(spetialization))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        List<Doctor> results = doctorQueryResults.getResults();
        long total = doctorQueryResults.getTotal();
        return new PageImpl<>(results,pageable,total);
    }

    private BooleanExpression hospitalnamecontain(String hospitalname){
        return ObjectUtils.isEmpty(hospitalname) ? null:hospital.name.contains(hospitalname);
    }

    private BooleanExpression spetializationeq(Spetialization spetialization){
        return ObjectUtils.isEmpty(spetialization) ? null:doctor.spetialization.eq(spetialization);
    }
}
