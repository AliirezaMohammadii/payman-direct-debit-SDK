package com.ighe3.api.aspect;

import com.ighe3.api.dto.client.response.error.ErrorResponse;
import com.ighe3.api.exception.PaymanCodeNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler  {

    @ExceptionHandler({PaymanCodeNotFoundException.class})
    public ResponseEntity<Object> handlePaymanCodeNotFoundException(PaymanCodeNotFoundException exception) {
        return createExceptionResponse(exception, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Object> createExceptionResponse(PaymanCodeNotFoundException exception, HttpStatus httpStatus) {
        ErrorResponse response = new ErrorResponse(exception.getCode(), exception.getMessage());
        return new ResponseEntity<>(response, new HttpHeaders(), httpStatus);
    }
}
