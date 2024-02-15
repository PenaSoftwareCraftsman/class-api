package com.unitech.classapi.application.exceptions;

public class UserAlreadyApprovedException extends RuntimeException{
    public UserAlreadyApprovedException(String message){
        super(message);
    }

}
