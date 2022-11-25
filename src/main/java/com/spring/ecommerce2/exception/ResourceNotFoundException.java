package com.spring.ecommerce2.exception;

import java.io.Serial;

public class ResourceNotFoundException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}