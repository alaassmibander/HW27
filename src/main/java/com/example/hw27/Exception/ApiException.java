package com.example.hw27.Exception;

public class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }
}
