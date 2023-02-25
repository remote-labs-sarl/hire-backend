package com.remotelabs.hire.exceptions;

public class HireBadRequestException extends RuntimeException{

    public HireBadRequestException(String message){
        super(message);
    }

    public HireBadRequestException(String message, Throwable throwable){
        super(message, throwable);
    }
}
