package org.academiadecodigo.javabank.exceptions;

/**
 * A generic java bank exception to be used as base for concrete types of exceptions
 *
 * @see Exception
 */
public class JavaBankException extends RuntimeException {

    /**
     * @see Exception#Exception(String)
     */
    public JavaBankException(String message) {
        super(message);
    }
}
