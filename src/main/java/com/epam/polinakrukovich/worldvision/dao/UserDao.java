package com.epam.polinakrukovich.worldvision.dao;

import com.epam.polinakrukovich.worldvision.dao.exception.DaoException;

/**
 * Specifies basic actions with User instance.
 */
public interface UserDao {
    void createUser(String userId) throws DaoException;
    void deleteUser(String userId) throws DaoException;
}
