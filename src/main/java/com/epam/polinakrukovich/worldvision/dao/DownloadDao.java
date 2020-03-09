package com.epam.polinakrukovich.worldvision.dao;


import com.epam.polinakrukovich.worldvision.dao.exception.DaoException;

public interface DownloadDao {
    void createDownload(String imageUrl, String userId) throws DaoException;
    int readDownloadsCountByCreationTime(int daysPassed) throws DaoException;
}
