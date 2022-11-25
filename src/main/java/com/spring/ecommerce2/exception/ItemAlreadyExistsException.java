package com.spring.ecommerce2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ItemAlreadyExistsException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1;

    public ItemAlreadyExistsException(String message) {
        super(message);
    }
}