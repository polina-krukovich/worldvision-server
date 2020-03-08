package com.epam.polinakrukovich.worldvision.dao;

public interface LikeDao {
    void createLike(int userId, int imageId);
    void deleteLike(int userId, int imageId);
}
