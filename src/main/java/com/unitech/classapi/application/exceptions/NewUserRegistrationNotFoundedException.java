package com.unitech.classapi.application.exceptions;

public class NewUserRegistrationNotFoundedException extends RuntimeException{
    public NewUserRegistrationNotFoundedException(String message){
        super(message);
    }
}
