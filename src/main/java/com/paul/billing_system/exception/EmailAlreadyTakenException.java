package com.paul.billing_system.exception;

public class EmailAlreadyTakenException extends Exception{
    public EmailAlreadyTakenException(String msg){
        super(msg);
    }
}
