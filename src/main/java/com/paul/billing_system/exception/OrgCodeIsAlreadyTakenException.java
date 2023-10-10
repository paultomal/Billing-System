package com.paul.billing_system.exception;

public class OrgCodeIsAlreadyTakenException extends Exception{
    public OrgCodeIsAlreadyTakenException(String msg){
        super(msg);
    }
}
