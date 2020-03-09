package com.epam.polinakrukovich.worldvision.dao.impl;

import com.epam.polinakrukovich.worldvision.dao.exception.DaoException;
import com.epam.polinakrukovich.worldvision.entity.Like;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class SqlLikeDaoTest {

    @Test
    public void testCreateLike() throws DaoException {
        SqlLikeDao dao = new SqlLikeDao();
        dao.createLike("test_image.com", "123");
    }

    @Test
    public void testDeleteLike() throws DaoException {
        SqlLikeDao dao = new SqlLikeDao();
        dao.deleteLike("test_image.com", "123");
    }

    @Test
    public void testReadLikesCountByCreationTime() throws DaoException {
        SqlLikeDao dao = new SqlLikeDao();
        dao.readLikesCountByCreationTime(50);
    }

    @Test
    public void testReadLikesByImage() throws DaoException {
        SqlLikeDao dao = new SqlLikeDao();
        List<Like> likes = dao.readLikesByImage("test_image.com");
        for (Like like : likes) {
            System.out.println(like);
        }
    }
}