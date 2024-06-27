package com.formation.banque.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.formation.banque.utils.FonctionnelleException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class BanqueExceptionHandler extends ResponseEntityExceptionHandler {

	
	@ExceptionHandler(value = { FonctionnelleException.class })
	ResponseEntity<Object> handleNotFoundException(HttpServletRequest request, Throwable ex) {
		logger.error("Erreur ", ex);
		return new ResponseEntity<>(ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
	}
}
