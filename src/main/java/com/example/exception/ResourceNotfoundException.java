package com.example.exception;

public class ResourceNotfoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ResourceNotfoundException(String msg) {
        super(msg);
    }

}
