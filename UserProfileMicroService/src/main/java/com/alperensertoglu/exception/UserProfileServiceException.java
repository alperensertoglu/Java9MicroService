package com.alperensertoglu.exception;

import lombok.Getter;

@Getter
public class UserProfileServiceException extends RuntimeException{
    private final EerrorType type;

    public UserProfileServiceException(EerrorType type){
        super(type.getMesaj());
        this.type=type;
    }
    public UserProfileServiceException(EerrorType type, String mesaj){
        super(mesaj);
        this.type=type;
    }

}
