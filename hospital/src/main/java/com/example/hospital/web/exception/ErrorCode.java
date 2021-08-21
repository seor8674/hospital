package com.example.hospital.web.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    NONE_USER("존재하지 않는 회원입니다."),
    DIFFERENT_PASSWORD("비밀번호가 일치하지 않습니다."),
    DUPLICATE_USER("이미 존재하는 회원입니다.")
    ,HANDLE_ACCESS_DENIED("권한이 없습니다.")
    ,REQUEST_PARAMETER_ERROR("요청 파라미터 예외입니다.")
    ,NONE_DATA("존재하지 않는 데이터입니다.");

    private String name;
}
