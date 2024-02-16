package com.unitech.classapi.application.exceptions;

public class InvalidEmailOrPasswordException extends RuntimeException {
    public InvalidEmailOrPasswordException(){
        super("Email or Password not founded.");
    }
}
