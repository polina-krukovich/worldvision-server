package com.epam.polinakrukovich.worldvision.dao;

import com.epam.polinakrukovich.worldvision.dao.exception.DaoException;

/**
 * Specifies basic actions with ImageColor instance.
 */
public interface ImageColorDao {
    void createImageColor(String imageUrl, int colorId, double score) throws DaoException;
}
