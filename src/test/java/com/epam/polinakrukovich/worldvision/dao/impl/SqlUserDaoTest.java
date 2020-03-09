package com.epam.polinakrukovich.worldvision.dao.impl;

import com.epam.polinakrukovich.worldvision.config.Config;
import com.epam.polinakrukovich.worldvision.dao.exception.DaoException;
import com.epam.polinakrukovich.worldvision.entity.User;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.testng.Assert.*;

public class SqlUserDaoTest {

    private Config config = Config.getInstance();

    @Test
    public void testCreateUser() throws DaoException {
        SqlUserDao dao = new SqlUserDao();
        User user = new User("123");
        dao.createUser(user.getId());
    }

    @Test
    public void testCheckUser() throws DaoException {
        SqlUserDao dao = new SqlUserDao();
        User user = new User("123");
        assertTrue(dao.checkUser(user.getId()));
    }

    @Test
    public void testReadUsersLikedImage() throws DaoException {
        SqlUserDao dao = new SqlUserDao();
        List<User> users = dao.readUsersLikedImage("test_image.com");
        assertEquals(users.size(), 1);
    }

    @Test
    public void testReadUsersDownloadedImage() throws DaoException {
        SqlUserDao dao = new SqlUserDao();
        List<User> users = dao.readUsersDownloadedImage("test_image.com");
        assertEquals(users.size(), 1);
    }

    @Test
    public void testDeleteUser() throws DaoException {
        SqlUserDao dao = new SqlUserDao();
        dao.deleteUser(new User("123").getId());
    }
}