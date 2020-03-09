package com.epam.polinakrukovich.worldvision.dao.impl;

import com.epam.polinakrukovich.worldvision.dao.exception.DaoException;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SqlTagDaoTest {

    @Test
    public void testCreateTag() throws DaoException {
        SqlTagDao dao = new SqlTagDao();
        dao.createTag("street");
    }
}