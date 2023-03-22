package com.max.cpfhandler.exceptions;

import com.max.cpfhandler.entities.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseStatus
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidCpfException.class)
    public ResponseEntity<ErrorMessage> invalidCpfException(InvalidCpfException exception, WebRequest request){

        ErrorMessage message = new ErrorMessage(exception.getClass().getName(), exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @ExceptionHandler(ExistsCpfException.class)
    public ResponseEntity<ErrorMessage> existsCpfException(ExistsCpfException exception, WebRequest request){

        ErrorMessage message = new ErrorMessage(exception.getClass().getName(), exception.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
    }

    @ExceptionHandler(NotFoundCpfException.class)
    public ResponseEntity<ErrorMessage> notFoundCpfException(NotFoundCpfException exception, WebRequest request){

        ErrorMessage message = new ErrorMessage(exception.getClass().getName(), exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

}
