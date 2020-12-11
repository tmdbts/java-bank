package org.academiadecodigo.javabank.exceptions;

/**
 * A generic Not Found exception to be used as base for concrete types of not found exceptions
 */
public class NotFoundException extends JavaBankException {

    /**
     * @see JavaBankException#JavaBankException(String)
     */
    public NotFoundException(String message) {
        super(message);
    }
}

