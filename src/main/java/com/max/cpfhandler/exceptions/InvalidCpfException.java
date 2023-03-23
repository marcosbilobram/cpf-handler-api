package com.max.cpfhandler.exceptions;

public class InvalidCpfException extends IllegalArgumentException {

    public InvalidCpfException() {
        super();
    }

    public InvalidCpfException(String s) {
        super(s);
    }

    public InvalidCpfException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidCpfException(Throwable cause) {
        super(cause);
    }
}
