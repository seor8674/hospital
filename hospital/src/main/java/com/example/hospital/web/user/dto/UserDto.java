package com.example.hospital.web.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {


    @NotNull(message = "아이디를 입력해주세요")
    private String userName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "비밀번호를 입력해주세요")
    private String password;

    @NotNull(message = "이름를 입력해주세요")
    private String name;

    @NotNull(message = "나이를 입력해주세요")
    private int age;


}
