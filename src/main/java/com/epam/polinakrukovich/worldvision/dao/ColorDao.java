package com.epam.polinakrukovich.worldvision.dao;

import com.epam.polinakrukovich.worldvision.dao.exception.DaoException;
import com.epam.polinakrukovich.worldvision.entity.Color;

import java.util.List;

public interface ColorDao {
    List<Color> readAllColors() throws DaoException;
    Color readColorById(int id) throws DaoException;
}
