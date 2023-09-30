package com.alperensertoglu.exception;

import lombok.Getter;

@Getter
public class AuthServiceException extends RuntimeException{
    private final EerrorType type;

    public AuthServiceException(EerrorType type){
        super(type.getMesaj());
        this.type=type;
    }
    public AuthServiceException(EerrorType type, String mesaj){
        super(mesaj);
        this.type=type;
    }

}
