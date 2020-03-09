package com.epam.polinakrukovich.worldvision.dao.impl;

import com.epam.polinakrukovich.worldvision.dao.exception.DaoException;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SqlDownloadDaoTest {

    @Test
    public void testCreateDownload() throws DaoException {
        SqlDownloadDao dao = new SqlDownloadDao();
        dao.createDownload("test_image.com", "123");
    }

    @Test
    public void testReadDownloadsCountByCreationTime() throws DaoException {
        SqlDownloadDao dao = new SqlDownloadDao();
        int count = dao.readDownloadsCountByCreationTime(2);
        assertNotEquals(count, 0);
    }
}