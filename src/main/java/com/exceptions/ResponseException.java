package com.exceptions;

public class ResponseException extends RuntimeException {
    public ResponseException(String message) {
        super(message);
    }
}
