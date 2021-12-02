package com.project.nix.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.xml.bind.ValidationException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler
{
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<String> handleAllException(Exception exception)
    {
        log.error("SERVER EXCEPTION: " + exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {AuthorityServiceException.class, UserServiceException.class,
            BillServiceException.class})
    public ResponseEntity<String> handleServiceException(Exception exception)
    {
        log.error("SERVICE EXCEPTION: " + exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {ValidationException.class})
    public ResponseEntity<String> handleValidationException(Exception exception)
    {
        log.error("VALIDATION EXCEPTION: " + exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}