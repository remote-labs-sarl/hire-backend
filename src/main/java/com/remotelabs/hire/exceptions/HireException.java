package com.remotelabs.hire.exceptions;

public class HireException extends RuntimeException{

    public HireException(String message){
        super(message);
    }

    public HireException(String message, Throwable throwable){
        super(message, throwable);
    }
}
