package com.mypisubproject.PiSub.Project.exceptions;

import org.springframework.http.HttpStatus;

public class BadCallException extends Exception{

    HttpStatus httpStatus;
    public BadCallException(String errorMessage){
        super(errorMessage);
    }

    public int setHttpStatus(HttpStatus internalServerError) {
        return setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
