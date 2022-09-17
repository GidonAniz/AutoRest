package com.autorest.autorest.web.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AppErrorResponse {
    public static final int CODE_ENTITY_NOT_FOUND = 104;
    private static final int CODE_ENTITY_ALREADY_FOUND = 105;
    private final long timestamp;
    private final String message;
    private final int code;

    private static AppErrorResponse ofNow(String message,int code){
        return new AppErrorResponse(System.currentTimeMillis(),message,code);
    }

    public static AppErrorResponse noEntityFound(String message){
        return ofNow(message,CODE_ENTITY_NOT_FOUND) ;
    }

    public static AppErrorResponse entityAlreadyFound(String message){
        return ofNow(message,CODE_ENTITY_ALREADY_FOUND) ;
    }
}
