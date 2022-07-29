package com.dockerspringbootv1.Entity;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class BaseResponse<T>{
    private HttpStatus status;
    private String message;
    private T object;

    public BaseResponse() {
    }

    public BaseResponse(HttpStatus status, String message, T object) {
        this.status = status;
        this.message = message;
        this.object = object;
    }
}
