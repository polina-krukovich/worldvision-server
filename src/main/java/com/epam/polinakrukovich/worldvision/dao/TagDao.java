package com.epam.polinakrukovich.worldvision.dao;

import com.epam.polinakrukovich.worldvision.dao.exception.DaoException;

public interface TagDao {
    void createTag(String tag) throws DaoException;
}
