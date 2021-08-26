package com.example.hospital.web.user.dto;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginDto {


    private String userName;


    private String password;
}
