package com.epam.polinakrukovich.worldvision.dao;

import com.epam.polinakrukovich.worldvision.dao.exception.DaoException;
import com.epam.polinakrukovich.worldvision.entity.User;

import java.util.List;

public interface UserDao {
    void createUser(String userId) throws DaoException;
    boolean checkUser(String userId) throws DaoException;
    List<User> readUsersLikedImage(String imageUrl) throws DaoException;
    List<User> readUsersDownloadedImage(String imageUrl) throws DaoException;
    void deleteUser(String userId) throws DaoException;
}
