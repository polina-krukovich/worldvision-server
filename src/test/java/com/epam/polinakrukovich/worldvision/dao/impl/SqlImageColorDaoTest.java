package com.epam.polinakrukovich.worldvision.dao.impl;

import com.epam.polinakrukovich.worldvision.dao.exception.DaoException;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SqlImageColorDaoTest {

    @Test
    public void testCreateImageColor() throws DaoException {
        SqlImageColorDao dao = new SqlImageColorDao();
        dao.createImageColor("test_image2.com", 7, 0.008);
    }
}