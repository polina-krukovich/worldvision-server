package com.epam.polinakrukovich.worldvision.dao.impl;

import com.epam.polinakrukovich.worldvision.dao.exception.DaoException;
import com.epam.polinakrukovich.worldvision.entity.Color;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class SqlColorDaoTest {

    @Test
    public void testReadAllColors() throws DaoException {
        SqlColorDao dao = new SqlColorDao();
        List<Color> colors = dao.readAllColors();
        for (Color color : colors) {
            System.out.println(color);
        }
    }

    @Test
    public void testReadColorById() throws DaoException {
        SqlColorDao dao = new SqlColorDao();
        Color color = dao.readColorById(1);
        System.out.println(color);
    }
}