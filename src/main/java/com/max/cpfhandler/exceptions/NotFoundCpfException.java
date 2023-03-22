package com.max.cpfhandler.exceptions;

public class NotFoundCpfException extends Exception{

    public NotFoundCpfException() {
        super();
    }

    public NotFoundCpfException(String message) {
        super(message);
    }

    public NotFoundCpfException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundCpfException(Throwable cause) {
        super(cause);
    }

    protected NotFoundCpfException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
