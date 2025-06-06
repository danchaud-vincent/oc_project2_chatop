package com.chatop.api.exception;

public class InvalidCredentialsException extends RuntimeException {

    public InvalidCredentialsException(String msg){
        super(msg);
    }

}
