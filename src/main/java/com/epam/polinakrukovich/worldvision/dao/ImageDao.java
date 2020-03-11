package com.epam.polinakrukovich.worldvision.dao;

import com.epam.polinakrukovich.worldvision.dao.exception.DaoException;
import com.epam.polinakrukovich.worldvision.entity.Image;
import org.joda.time.DateTime;

import java.util.List;

public interface ImageDao {
    void createImage(String url, String userId) throws DaoException;
    Image[] readImageByUser(String userId) throws DaoException;
    Image[] readImageTop() throws DaoException;
    Image[] readImageByTag(String tag) throws DaoException;
    Image[] readImageByColor(int colorId) throws DaoException;
    Image[] readImageByCreationTime(int daysPassed) throws DaoException;
    void deleteImage(String url) throws DaoException;
}
