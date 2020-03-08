package com.epam.polinakrukovich.worldvision.dao.impl;

import com.epam.polinakrukovich.worldvision.dao.SqlDao;
import com.epam.polinakrukovich.worldvision.dao.UserDao;

public class SqlUserDao extends SqlDao implements UserDao {
    @Override
    public void createUser(String uid) {

    }

    @Override
    public int deleteUser(String uid) {
        return 0;
    }
}
