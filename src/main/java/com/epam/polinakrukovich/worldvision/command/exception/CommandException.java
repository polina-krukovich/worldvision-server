package com.epam.polinakrukovich.worldvision.command.exception;

/**
 * Exception class for command layer.
 *
 * @see Exception
 *
 * @author Polina Krukovich
 */
public class CommandException extends Exception {
    static final long serialVersionUID = -5663143139119042393L;

    public CommandException(String message) {
        super(message);
    }
}
