package com.demo.item.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GeneralExceptionAdvisor extends ResponseEntityExceptionHandler {


    @ExceptionHandler(GenericException.class)
    private ResponseEntity<?> handleNotFound(GenericException ex){
        Map<String, Object> errors = new HashMap<>();
        errors.put("error", ex.getErrorMessage());
        errors.put("errorCode", ex.getErrorCode());
        return ResponseEntity
                .status(ex.getHttpStatus() != null ?  ex.getHttpStatus() : HttpStatus.BAD_REQUEST)
                .body(errors);    }
}
