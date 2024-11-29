package com.example.parking_web_application.exceptions;

public class DuplicatePlateNumberException extends RuntimeException{
    public DuplicatePlateNumberException(String message) {
        super(message);
    }
}
