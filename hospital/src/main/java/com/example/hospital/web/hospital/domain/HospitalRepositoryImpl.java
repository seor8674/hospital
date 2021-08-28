package com.example.hospital.web.hospital.domain;

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

import static com.example.hospital.web.hospital.domain.QHospital.*;

public class HospitalRepositoryImpl implements HospitalRepositoryCustom{

    @Autowired
    EntityManager entityManager;


    private final JPAQueryFactory jpaQueryFactory;

    public HospitalRepositoryImpl(EntityManager entityManager) {
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Page<Hospital> findAllHospital(Pageable pageable) {
        QueryResults<Hospital> hospitalQueryResults = jpaQueryFactory.selectFrom(hospital)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
        List<Hospital> results = hospitalQueryResults.getResults();
        long total = hospitalQueryResults.getTotal();
        return new PageImpl<>(results,pageable,total);
    }

    @Override
    public Page<Hospital> findHospitalByname(Pageable pageable, String name) {
        QueryResults<Hospital> hospitalQueryResults = jpaQueryFactory.selectFrom(hospital)
                .where(namecontain(name))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize()).fetchResults();
        List<Hospital> results = hospitalQueryResults.getResults();
        long total = hospitalQueryResults.getTotal();
        return new PageImpl<>(results,pageable,total);
    }

    @Override
    public Page<Hospital> findHospitalByaddress(Pageable pageable, String address) {
        QueryResults<Hospital> hospitalQueryResults = jpaQueryFactory.selectFrom(hospital)
                .where(addresscontain(address))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize()).fetchResults();
        List<Hospital> results = hospitalQueryResults.getResults();
        long total = hospitalQueryResults.getTotal();
        return new PageImpl<>(results,pageable,total);
    }

    @Override
    public Page<Hospital> findHospitalByaddressandname(Pageable pageable, String address, String name) {
        QueryResults<Hospital> hospitalQueryResults = jpaQueryFactory.selectFrom(hospital)
                .where(addresscontain(address),namecontain(name))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize()).fetchResults();
        List<Hospital> results = hospitalQueryResults.getResults();
        long total = hospitalQueryResults.getTotal();
        return new PageImpl<>(results,pageable,total);
    }

    private BooleanExpression addresscontain(String address){
        return ObjectUtils.isEmpty(address) ? null: hospital.address.contains(address);
    }

    private BooleanExpression namecontain(String name){
        return ObjectUtils.isEmpty(name) ? null: hospital.name.contains(name);
    }
}
