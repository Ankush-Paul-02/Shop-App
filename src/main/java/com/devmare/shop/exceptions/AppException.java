package com.devmare.shop.exceptions;

public class AppException extends RuntimeException {

    AppException() {
        super();
    }

    public AppException(String message) {
        super(message);
    }
}
