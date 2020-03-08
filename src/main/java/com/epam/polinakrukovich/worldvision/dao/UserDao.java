package com.epam.polinakrukovich.worldvision.dao;

public interface UserDao {
    void createUser(String uid);
    int deleteUser(String uid);
}
