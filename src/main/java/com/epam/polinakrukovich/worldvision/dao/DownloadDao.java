package com.epam.polinakrukovich.worldvision.dao;

import com.epam.polinakrukovich.worldvision.dao.exception.DaoException;

/**
 * Specifies basic actions with Download instance.
 */
public interface DownloadDao {
    void createDownload(String imageUrl, String userId) throws DaoException;
    int readDownloadsCountByCreationTime(int daysPassed) throws DaoException;
}
