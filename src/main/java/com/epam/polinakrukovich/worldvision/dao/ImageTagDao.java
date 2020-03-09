package com.epam.polinakrukovich.worldvision.dao;

import com.epam.polinakrukovich.worldvision.dao.exception.DaoException;

public interface ImageTagDao {
    void createImageTag(String imageUrl, String tag) throws DaoException;
}
