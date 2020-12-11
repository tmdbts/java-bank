package org.academiadecodigo.javabank.exceptions;

/**
 * Thrown to indicate that the recipient was not found
 */
public class RecipientNotFoundException extends NotFoundException {

    /**
     * @see NotFoundException#NotFoundException(String)
     */
    public RecipientNotFoundException() {
        super("Recipient Not Found");
    }
}
