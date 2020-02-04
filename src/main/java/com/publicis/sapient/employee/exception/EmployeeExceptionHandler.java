package com.publicis.sapient.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeExceptionHandler {

    @ExceptionHandler(value = NoRecordFoundException.class)
    public ResponseEntity<Object> noRecordFoundExceptionHandler() {
        return new ResponseEntity<>("No record found", HttpStatus.NOT_FOUND);
    }
}
