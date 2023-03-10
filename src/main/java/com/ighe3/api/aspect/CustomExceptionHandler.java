package com.ighe3.api.aspect;

import com.ighe3.api.dto.ExceptionResponse;
import com.ighe3.api.exception.PaymanCodeNotFoundException;
import com.ighe3.api.utils.GeneralUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler  {

    @ExceptionHandler({PaymanCodeNotFoundException.class})
    public ResponseEntity<Object> handlePaymanCodeNotFoundException(PaymanCodeNotFoundException exception) {
        return createExceptionResponse(exception, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Object> createExceptionResponse(PaymanCodeNotFoundException exception, HttpStatus httpStatus) {
        ExceptionResponse response = new ExceptionResponse(exception.getCode(), exception.getMessage());
        return new ResponseEntity<>(response, new HttpHeaders(), httpStatus);
    }
}
