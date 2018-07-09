package com.elina.railwayApp.exception;

import lombok.Getter;

public class BusinessLogicException extends Exception {

    @Getter
    private String error;

    public BusinessLogicException(String error){
        this.error = error;
    }
}
