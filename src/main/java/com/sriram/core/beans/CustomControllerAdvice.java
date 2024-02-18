package com.sriram.core.beans;

import com.sriram.core.exception.CustomException;
import com.sriram.core.response.BaseRestResponse;
import com.sriram.core.response.ErrorResponse;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import io.sentry.Sentry;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<BaseRestResponse> handleCustomExceptions(Exception e) {

        CustomException customException = (CustomException) e;
        if (!((CustomException) e).getStatus().is2xxSuccessful()) {
            Sentry.captureException(e);
        }
        HttpStatus status = customException.getStatus();

        BaseRestResponse<Object> response = BaseRestResponse.builder()
            .ok(Boolean.FALSE)
            .error(new ErrorResponse(
                status,
                customException.getMessage()
            ))
            .build();

        return new ResponseEntity<>(
            response,
            status
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleExceptions(Exception e) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // 500
        Sentry.captureException(e);
        BaseRestResponse<Object> response = BaseRestResponse.builder()
            .ok(Boolean.FALSE)
            .error(new ErrorResponse(
                status,
                e.getClass().getPackageName().contains("com.sriram") ? e.getMessage()
                    : "Something went wrong."
            ))
            .build();

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);
        String stackTrace = stringWriter.toString();
        logger.error(stackTrace);

        return new ResponseEntity<>(
            response,
            status
        );
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity genericExceptionHandler(SQLException sqlException, WebRequest webRequest)
    {
        Sentry.captureException(sqlException);
        BaseRestResponse<Object> response = BaseRestResponse.builder()
                .ok(Boolean.FALSE)
                .error(new ErrorResponse(
                        HttpStatus.EXPECTATION_FAILED,
                        sqlException.getMessage()
                ))
                .build();

        return new ResponseEntity<>(
                response,
                HttpStatus.EXPECTATION_FAILED
        );
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity genericResponseStatusExceptionHandler(ResponseStatusException responseStatusException)
    {
        Sentry.captureException(responseStatusException);
        BaseRestResponse<Object> response = BaseRestResponse.builder()
                .ok(Boolean.FALSE)
                .error(new ErrorResponse(
                        HttpStatus.valueOf(responseStatusException.getStatusCode().value()),
                        responseStatusException.getMessage()
                ))
                .build();

        return new ResponseEntity<>(
                response,
                HttpStatus.valueOf(responseStatusException.getStatusCode().value())
        );
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity genericExceptionHandler(FileNotFoundException fileNotFoundException, WebRequest webRequest)
    {
        Sentry.captureException(fileNotFoundException);
        BaseRestResponse<Object> response = BaseRestResponse.builder()
                .ok(Boolean.FALSE)
                .error(new ErrorResponse(
                        HttpStatus.NOT_FOUND,
                        fileNotFoundException.getMessage()
                ))
                .build();

        return new ResponseEntity<>(
                response,
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity genericExceptionHandler(IllegalArgumentException illegalArgumentException, WebRequest webRequest)
    {

        Sentry.captureException(illegalArgumentException);
        BaseRestResponse<Object> response = BaseRestResponse.builder()
                .ok(Boolean.FALSE)
                .error(new ErrorResponse(
                        HttpStatus.METHOD_NOT_ALLOWED,
                        illegalArgumentException.getMessage()
                ))
                .build();

        return new ResponseEntity<>(
                response,
                HttpStatus.METHOD_NOT_ALLOWED
        );

    }

    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException methodArgumentNotValidException, HttpHeaders headers, HttpStatus status, WebRequest webRequest)
    {
        Sentry.captureException(methodArgumentNotValidException);
        BaseRestResponse<Object> response = BaseRestResponse.builder()
                .ok(Boolean.FALSE)
                .error(new ErrorResponse(
                        HttpStatus.BAD_REQUEST,
                        methodArgumentNotValidException.getMessage()
                ))
                .build();

        return new ResponseEntity<>(
                response,
                HttpStatus.BAD_REQUEST
        );

    }

}
