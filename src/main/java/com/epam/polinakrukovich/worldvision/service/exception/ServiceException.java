package com.epam.polinakrukovich.worldvision.service.exception;

/**
 * Exception class for {@code Service} layer.
 *
 * @see Exception
 *
 * @author Polina Krukovich
 */
public class ServiceException extends Exception {
    static final long serialVersionUID = 3594442575177538644L;

    public ServiceException(String message) {
        super(message);
    }
}
