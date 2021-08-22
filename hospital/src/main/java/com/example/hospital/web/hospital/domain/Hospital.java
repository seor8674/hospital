package com.example.hospital.web.hospital.domain;

import com.example.hospital.web.doctor.domain.Doctor;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hospital_id")
    private Long id;

    private String name;

    private String address;

    @OneToMany(mappedBy = "hospital")
    List<Doctor> doctors=new ArrayList<>();

    public Hospital(String name, String address) {
        this.name = name;
        this.address=address;
    }
}
