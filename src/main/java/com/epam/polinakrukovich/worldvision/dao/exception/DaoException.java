package com.epam.polinakrukovich.worldvision.dao.exception;

/**
 * Exception class for Data Access layer.
 *
 * @see Exception
 *
 * @author Polina Krukovich
 */
public class DaoException extends Exception {
    static final long serialVersionUID = -6194660152382456773L;

    public DaoException(String message) {
        super(message);
    }
}
