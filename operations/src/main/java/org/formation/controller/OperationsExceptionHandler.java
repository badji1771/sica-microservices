package org.formation.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class OperationsExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value = {EntityNotFoundException.class})
    ResponseEntity<Object> handleNotFoundException(HttpServletRequest request, Throwable ex) {
        logger.error("Erreur ", ex);
        return new ResponseEntity<>(ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
}
