package com.example.hospital.config.auth;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Setter
@Table(name ="authority")
public class Authority {

    @Id
    @Column(name = "authority_name")
    private String authorityName;


}
