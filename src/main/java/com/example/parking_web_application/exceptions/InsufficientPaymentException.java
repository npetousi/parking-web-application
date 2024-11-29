package com.example.parking_web_application.exceptions;

public class InsufficientPaymentException extends RuntimeException{
    public InsufficientPaymentException(String message){
        super(message);
    }

}
