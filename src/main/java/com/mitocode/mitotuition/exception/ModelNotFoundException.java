package com.mitocode.mitotuition.exception;

//@ResponseStatus(org.springframework.http.HttpStatus.NOT_FOUND)
public class ModelNotFoundException extends RuntimeException {



    public ModelNotFoundException(String message) {
        super(message);
    }

}
