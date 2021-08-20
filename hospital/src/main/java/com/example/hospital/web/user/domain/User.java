package com.example.hospital.web.user.domain;

import com.example.hospital.config.auth.Authority;
import com.example.hospital.web.reservation.domain.Reservation;
import com.example.hospital.web.reservation.dto.ReservationRequestDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user")
public class User {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(unique = true)
    private String userName;

    private String password;

    private int age;

    private String name;

    @JsonIgnore
    private boolean activated;

    @ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;

    @OneToMany(mappedBy = "user")
    List<Reservation> reservationList=new ArrayList<>();

    public User(String userName, String password, int age, String name, boolean activated) {
        this.userName = userName;
        this.password = password;
        this.age = age;
        this.name = name;
        this.activated = activated;
    }
}
