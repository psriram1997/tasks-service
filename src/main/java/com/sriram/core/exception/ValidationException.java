package com.sriram.core.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ValidationException extends CustomException {

    public ValidationException(HttpStatus httpStatus, String message) {
        super(httpStatus, message);
    }

    public ValidationException(String message) {
        super(message);
    }
}
