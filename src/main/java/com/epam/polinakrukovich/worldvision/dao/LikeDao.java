package com.epam.polinakrukovich.worldvision.dao;

import com.epam.polinakrukovich.worldvision.dao.exception.DaoException;
import com.epam.polinakrukovich.worldvision.entity.Like;

import java.util.List;

public interface LikeDao {
    void createLike(String imageUrl, String userId) throws DaoException;
    void deleteLike(String imageUrl, String userId) throws DaoException;
    int readLikesCountByCreationTime(int daysPassed) throws DaoException;
    List<Like> readLikesByImage(String imageUrl) throws DaoException;
}
