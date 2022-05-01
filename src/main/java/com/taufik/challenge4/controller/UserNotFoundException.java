package com.taufik.challenge4.controller;

public class UserNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String message;
    public UserNotFoundException(String message) {
        this.message = message;
    }
}
