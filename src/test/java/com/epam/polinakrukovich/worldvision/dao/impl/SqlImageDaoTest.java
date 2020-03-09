package com.epam.polinakrukovich.worldvision.dao.impl;

import com.epam.polinakrukovich.worldvision.dao.exception.DaoException;
import com.epam.polinakrukovich.worldvision.entity.Image;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class SqlImageDaoTest {

    @Test
    public void testCreateImage() throws DaoException {
        SqlImageDao dao = new SqlImageDao();
        String url = "test_image2.com";
        String userId = "123";
        dao.createImage(url, userId);
    }

    @Test
    public void testReadImageByUser() throws DaoException {
        SqlImageDao dao = new SqlImageDao();
        List<Image> images = dao.readImageByUser("123", 0, 50);
        System.out.println(images.get(0));
    }

    @Test
    public void testReadImageTop() throws DaoException {
        SqlImageDao dao = new SqlImageDao();
        List<Image> images = dao.readImageTop(0, 50);
        for (Image image : images) System.out.println(image);
    }

    @Test
    public void testReadImageByTag() throws DaoException {
        SqlImageDao dao = new SqlImageDao();
        List<Image> images = dao.readImageByTag("sky", 0, 50);
        for (Image image : images) System.out.println(image);
    }

    @Test
    public void testReadImageByColor() throws DaoException {
        SqlImageDao dao = new SqlImageDao();
        List<Image> images = dao.readImageByColor(2, 0, 50);
        for (Image image : images) System.out.println(image);
    }

    @Test
    public void testReadImageByCreationTime() throws DaoException {
        SqlImageDao dao = new SqlImageDao();
        List<Image> images = dao.readImageByCreationTime(50, 0, 50);
        for (Image image : images) System.out.println(image);
    }

    @Test
    public void testDeleteImage() throws DaoException {
        SqlImageDao dao = new SqlImageDao();
        dao.deleteImage("test_image3.com");
    }
}