package com.praktika.demoproj.demo1.service.geocoding.exception;

public class GeocodingException extends Exception {
    public GeocodingException(String message) {
        super(message);
    }

    public GeocodingException(String message, Throwable cause) {
        super(message, cause);
    }
}