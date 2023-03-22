package com.max.cpfhandler.exceptions;

import jakarta.persistence.EntityExistsException;

public class ExistsCpfException extends EntityExistsException {

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
}
