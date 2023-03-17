package com.wizer.wizerassessment.exceptions;


public class FieldValidationException extends RuntimeException {
    public FieldValidationException(String message){
        super(message);
    }
}