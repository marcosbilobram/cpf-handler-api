package com.max.cpfhandler.exceptions;

import org.hibernate.PropertyValueException;

public class InvalidCpfException extends PropertyValueException {

    public InvalidCpfException(String message, String entityName, String propertyName) {
        super(message, entityName, propertyName);
    }

    @Override
    public String getEntityName() {
        return super.getEntityName();
    }

    @Override
    public String getPropertyName() {
        return super.getPropertyName();
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
