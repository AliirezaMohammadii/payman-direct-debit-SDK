package com.ighe3.api.aspect;

import com.ighe3.api.dto.client.response.error.ErrorResponse;
import com.ighe3.api.exception.BaseException;
import com.ighe3.api.exception.InternalException;
import com.ighe3.api.exception.PaymanException;
import com.ighe3.api.exception.enums.ExceptionCodes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionTranslator extends ResponseEntityExceptionHandler  {

    @ExceptionHandler({PaymanException.class})
    public ResponseEntity<Object> handlePaymanException(PaymanException exception) {
        return createExceptionResponse(exception, HttpStatus.valueOf(exception.getStatusCode()));
    }

    @ExceptionHandler({InternalException.class})
    public ResponseEntity<Object> handleInternalException(InternalException exception) {
        return createExceptionResponse(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleException(Exception exception) {
        return createExceptionResponse(exception);
    }

    private ResponseEntity<Object> createExceptionResponse(BaseException exception, HttpStatus httpStatus) {
        ErrorResponse response = new ErrorResponse(exception.getCode(), exception.getMessage());
        return new ResponseEntity<>(response, new HttpHeaders(), httpStatus);
    }

    private ResponseEntity<Object> createExceptionResponse(Exception exception) {
        ErrorResponse response = new ErrorResponse(ExceptionCodes.INTERNAL_EXCEPTION.code, exception.getMessage());
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
