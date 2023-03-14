package com.ighe3.api.aspect;

import com.ighe3.api.dto.client.response.error.ErrorResponse;
import com.ighe3.api.exception.BaseException;
import com.ighe3.api.exception.PaymanException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Locale;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler  {

    @ExceptionHandler({PaymanException.class})
    public ResponseEntity<Object> handlePaymanException(PaymanException exception) {
        return createExceptionResponse(exception, HttpStatus.valueOf(exception.getStatusCode()));
    }

    private ResponseEntity<Object> createExceptionResponse(BaseException exception, HttpStatus httpStatus) {
        ErrorResponse response = new ErrorResponse(exception.getCode(), exception.getMessage());
        return new ResponseEntity<>(response, new HttpHeaders(), httpStatus);
    }
}
