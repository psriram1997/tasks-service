package com.sriram.core.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
@Builder
@AllArgsConstructor
public class ErrorResponse implements Serializable {

    private static final long serialVersionUID = 3899837650484970220L;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date timestamp;

    private int code;

    private String status;

    private String message;

    public ErrorResponse() {
        timestamp = new Date();
    }

    public ErrorResponse(
        HttpStatus httpStatus,
        String message
    ) {
        this();
        this.code = httpStatus.value();
        this.status = httpStatus.name();
        this.message = message;
    }
}

