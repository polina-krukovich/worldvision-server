package com.epam.polinakrukovich.worldvision.dao.impl;

import com.epam.polinakrukovich.worldvision.dao.exception.DaoException;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SqlImageTagDaoTest {

    @Test
    public void testCreateImageTag() throws DaoException {
        SqlImageTagDao dao = new SqlImageTagDao();
        dao.createImageTag("test_image2.com", "street");
    }
}