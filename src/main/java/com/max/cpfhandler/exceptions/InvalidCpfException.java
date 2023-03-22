package com.max.cpfhandler.exceptions;

public class InvalidCpfException extends Exception{

    public InvalidCpfException() {
        super();
    }

    public InvalidCpfException(String message) {
        super(message);
    }

    public InvalidCpfException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidCpfException(Throwable cause) {
        super(cause);
    }

    protected InvalidCpfException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
