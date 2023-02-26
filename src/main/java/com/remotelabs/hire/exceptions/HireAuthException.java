package com.remotelabs.hire.exceptions;

public class HireAuthException extends RuntimeException{

    public HireAuthException(String message){
        super(message);
    }

    public HireAuthException(String message, Throwable throwable){
        super(message, throwable);
    }
}
