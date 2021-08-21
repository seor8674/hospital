package com.example.hospital.web.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GlobalApiException extends RuntimeException{
    private String message;
    public GlobalApiException(ErrorCode errorCode){
        this.message=errorCode.getName();
    }
    public GlobalApiException(String message){
        this.message=message;
    }
}
