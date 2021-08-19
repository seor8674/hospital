package com.example.hospital.web.user.dto;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginDto {

    @NotNull
    private String userName;

    @NotNull
    private String password;
}
