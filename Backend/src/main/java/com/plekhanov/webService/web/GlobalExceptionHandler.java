package com.plekhanov.webService.web;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Обработчик ошибок, выбрасываемых из контроллеров
 */
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException() {

        return new ResponseEntity<>("Some Error Message", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
