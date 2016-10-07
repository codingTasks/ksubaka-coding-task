package com.ksubaka.codingtask.webapi;

public class MovieNotFoundException extends Exception {
    private static final String message = "Movie not found";

    public MovieNotFoundException() {
        super(message);
    }

    public MovieNotFoundException(String message) {
        super(message);
    }
}
