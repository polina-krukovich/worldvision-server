package com.epam.polinakrukovich.worldvision.dao;

import com.epam.polinakrukovich.worldvision.dao.exception.DaoException;
import com.epam.polinakrukovich.worldvision.entity.Image;
import org.joda.time.DateTime;

import java.util.List;

public interface ImageDao {
    void createImage(String url, String userId) throws DaoException;
    List<Image> readImageByUser(String userId, int pageIndex, int pageSize) throws DaoException;
    List<Image> readImageTop(int pageIndex, int pageSize) throws DaoException;
    List<Image> readImageByTag(String tag, int pageIndex, int pageSize) throws DaoException;
    List<Image> readImageByColor(int colorId, int pageIndex, int pageSize) throws DaoException;
    List<Image> readImageByCreationTime(int daysPassed, int pageIndex, int pageSize) throws DaoException;
    void deleteImage(String url) throws DaoException;
}
