package com.paul.billing_system.exception;

public class UserNameAlreadyTakenException extends Exception{
    public UserNameAlreadyTakenException(String msg){
        super(msg);
    }
}
