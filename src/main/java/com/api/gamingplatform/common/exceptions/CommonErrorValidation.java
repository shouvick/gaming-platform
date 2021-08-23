package com.api.gamingplatform.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CommonErrorValidation extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private String message;

    public CommonErrorValidation(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
