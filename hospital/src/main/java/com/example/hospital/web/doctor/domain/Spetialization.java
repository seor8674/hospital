package com.example.hospital.web.doctor.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Spetialization {

    Dermatology("피부과"),
    internal("내과"),
    Surgical("외과"),
    Neurosurgery("신경외과"),
    plastic_surgeon("성형외과"),
    psychiatry("정신과"),
    Dentistry("치과");
    private String name;
}
