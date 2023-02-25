package com.remotelabs.hire.exceptions;

public class HireInternalException extends RuntimeException{

    public HireInternalException(String message){
        super(message);
    }

    public HireInternalException(String message, Throwable throwable){
        super(message, throwable);
    }
}
