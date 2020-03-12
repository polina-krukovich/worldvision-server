package com.epam.polinakrukovich.worldvision.dao;

import com.epam.polinakrukovich.worldvision.dao.exception.DaoException;

/**
 * Specifies basic actions with ImageTag instance.
 */
public interface ImageTagDao {
    void createImageTag(String imageUrl, String tag) throws DaoException;
}
