package com.sriram.core.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class DatabaseException extends CustomException {

    public DatabaseException(HttpStatus httpStatus, String message) {
        super(httpStatus, message);
    }

    public DatabaseException(String message) {
        super(message);
    }


}
