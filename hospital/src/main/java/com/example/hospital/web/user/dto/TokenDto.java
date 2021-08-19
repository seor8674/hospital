package com.example.hospital.web.user.dto;


import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto {

    private String token;
}
