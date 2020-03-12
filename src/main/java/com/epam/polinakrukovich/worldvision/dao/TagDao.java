package com.epam.polinakrukovich.worldvision.dao;

import com.epam.polinakrukovich.worldvision.dao.exception.DaoException;

/**
 * Specifies basic actions with Tag instance.
 */
public interface TagDao {
    void createTag(String tag) throws DaoException;
}
