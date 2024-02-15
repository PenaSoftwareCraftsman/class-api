package com.unitech.classapi.application.exceptions;

public class UserAlreadyDeniedException extends RuntimeException{
    public UserAlreadyDeniedException(String message){
        super(message);
    }
}
