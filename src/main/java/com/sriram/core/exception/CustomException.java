package com.sriram.core.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class CustomException extends RuntimeException {

    private HttpStatus status = null;

    private String message = null;

    private Integer code;

    public CustomException() {
        super();
    }

    public CustomException(HttpStatus status, String message) {
        this.message = message;
        this.status = status;
    }

    public CustomException(String message) {
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
        this.message = message;
    }

    public CustomException(HttpStatus status, String message, Integer code) {
        this.status = status;
        this.message = message;
        this.code = code;
    }
}
