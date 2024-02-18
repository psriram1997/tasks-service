package com.sriram.core.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(Include.NON_NULL)
public class BaseRestResponse<T> implements Serializable {
    private static final long serialVersionUID = 3899837650484970219L;

    private Boolean ok;

    private String code;

    private T result;

    private ErrorResponse error;

    public BaseRestResponse(T result, String code) {

        this.ok = true;
        this.code = code;
        this.result = result;
        this.error = null;
    }

}
