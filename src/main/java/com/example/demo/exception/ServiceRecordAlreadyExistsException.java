package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ServiceRecordAlreadyExistsException extends RuntimeException {
    public ServiceRecordAlreadyExistsException(String message) { super(message); }
}
