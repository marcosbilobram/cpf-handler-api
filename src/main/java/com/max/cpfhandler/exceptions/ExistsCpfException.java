package com.max.cpfhandler.exceptions;

public class ExistsCpfException extends Exception{

    public ExistsCpfException() {
        super();
    }

    public ExistsCpfException(String message) {
        super(message);
    }

    public ExistsCpfException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExistsCpfException(Throwable cause) {
        super(cause);
    }

    protected ExistsCpfException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
