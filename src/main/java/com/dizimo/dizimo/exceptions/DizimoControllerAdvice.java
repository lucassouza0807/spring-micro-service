package com.dizimo.dizimo.exceptions;

import java.util.HashMap;

import java.util.Map;
/**
 * DizimoControllerAdvice
 */
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class DizimoControllerAdvice {
  @ResponseBody
    @ExceptionHandler(DizimoNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    Object employeeNotFoundHandler(DizimoNotFoundException ex) {
        Map<String, Object> response = new HashMap<>();

        response.put("message", ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

      }

}