package com.autorest.autorest.web.advice;

import com.autorest.autorest.service.ex.ClientAlreadyExist;
import com.autorest.autorest.service.ex.ClientOrVehicleNotFound;
import com.autorest.autorest.web.response.AppErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppControllerAdvice {

    @ExceptionHandler(ClientOrVehicleNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    AppErrorResponse handleEntityNotFound(RuntimeException e) {
        return AppErrorResponse.noEntityFound(e.getMessage());
    }

    @ExceptionHandler(ClientAlreadyExist.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    AppErrorResponse handleEntityAlreadyFound(RuntimeException e) {
        return AppErrorResponse.entityAlreadyFound(e.getMessage());
    }
}
