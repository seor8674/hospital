package com.example.hospital.web.doctor.domain;

import com.example.hospital.web.hospital.domain.Hospital;
import com.example.hospital.web.reservation.domain.Reservation;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
    private Long id;

    private String name;

    private Spetialization spetialization;

    private int period;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @OneToMany(mappedBy = "doctor")
    List<Reservation> reservationList=new ArrayList<>();

    public Doctor(String name, Spetialization spetialization, int period, Hospital hospital) {
        this.name = name;
        this.spetialization = spetialization;
        this.period = period;
        this.hospital = hospital;
    }

    public void changeHospital(Hospital hospital){
        this.hospital=hospital;
    }
}
