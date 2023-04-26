package com.payman.api.exception;

import com.payman.api.dto.client.response.error.ErrorResponse;
import com.payman.api.exception.enums.ExceptionCodes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionTranslator extends ResponseEntityExceptionHandler {


    // TODO: 4/18/23 you can implement this method that help you to catch invalid data from controller
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        InternalException internalException = new InternalException(ExceptionCodes.INVALID_REQUEST_ARGUMENT);
        return createExceptionResponse(internalException, HttpStatus.BAD_REQUEST);
    }

    // TODO: 4/18/23 don't return object. create BaseException class that includes error code and error message and something like that
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
        InternalException internalException = new InternalException(ExceptionCodes.UNKNOWN_EXCEPTION);
        return createExceptionResponse(internalException, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<Object> createExceptionResponse(BaseException exception, HttpStatus httpStatus) {
        ErrorResponse response = new ErrorResponse(exception.getCode(), exception.getMessage(), exception.getInfo());
        return new ResponseEntity<>(response, new HttpHeaders(), httpStatus);
    }
}
