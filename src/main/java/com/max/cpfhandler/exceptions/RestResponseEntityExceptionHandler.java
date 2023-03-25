package com.max.cpfhandler.exceptions;

import com.max.cpfhandler.entities.ErrorMessage;
import org.springframework.dao.DataIntegrityViolationException;
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

    private String exceptionClassName;

    @ExceptionHandler(InvalidCpfException.class)
    public ResponseEntity<ErrorMessage> invalidCpfException(InvalidCpfException exception, WebRequest request){

        exceptionClassName = InvalidCpfException.class.getName();

        ErrorMessage message = new ErrorMessage(exceptionClassName.
                                substring(exceptionClassName.lastIndexOf(".") + 1),
                                exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @ExceptionHandler(ExistsCpfException.class)
    public ResponseEntity<ErrorMessage> existsCpfException(ExistsCpfException exception, WebRequest request){

        exceptionClassName = ExistsCpfException.class.getName();

        ErrorMessage message = new ErrorMessage(exceptionClassName.
                                    substring(exceptionClassName.lastIndexOf(".") + 1),
                                    exception.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
    }

    @ExceptionHandler(NotFoundCpfException.class)
    public ResponseEntity<ErrorMessage> notFoundCpfException(NotFoundCpfException exception, WebRequest request){

        exceptionClassName = NotFoundCpfException.class.getName();

        ErrorMessage message = new ErrorMessage(exceptionClassName.
                                    substring(exceptionClassName.lastIndexOf(".") + 1),
                                    exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorMessage> dataIntegrityViolation(DataIntegrityViolationException exception, WebRequest request){

        exceptionClassName = DataIntegrityViolationException.class.getName();

        ErrorMessage message = new ErrorMessage(exceptionClassName.
                substring(exceptionClassName.lastIndexOf(".") + 1),
                exception.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
    }

}
