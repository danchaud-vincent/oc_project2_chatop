package com.chatop.api.exception;

public class RentalNotFoundException extends RuntimeException {

    public RentalNotFoundException(String msg){
        super(msg);
    }

}
