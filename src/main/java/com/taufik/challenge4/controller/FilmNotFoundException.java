package com.taufik.challenge4.controller;

public class FilmNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String message;
    public FilmNotFoundException(String message) {
        this.message = message;
    }
}
