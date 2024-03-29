package com.visa.vdp.exception;

public class ApplicationException extends RuntimeException{
    private final String errorCode;
    public ApplicationException(String errorCode, String errorMessage, Throwable e){
        super(errorMessage,e);
        this.errorCode = errorCode;
    }

    public ApplicationException(String errorCode, String errorMessage){
        super(errorMessage);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
