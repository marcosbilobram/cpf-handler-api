package com.max.highriskcpf.exceptions;

import java.util.NoSuchElementException;

public class NotFoundCpfException extends NoSuchElementException {

    public NotFoundCpfException() {
        super();
    }

    public NotFoundCpfException(String s, Throwable cause) {
        super(s, cause);
    }

    public NotFoundCpfException(Throwable cause) {
        super(cause);
    }

    public NotFoundCpfException(String s) {
        super(s);
    }
}
