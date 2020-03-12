package com.epam.polinakrukovich.worldvision.service;

import com.epam.polinakrukovich.worldvision.dao.DownloadDao;
import com.epam.polinakrukovich.worldvision.dao.exception.DaoException;
import com.epam.polinakrukovich.worldvision.dao.factory.DaoFactory;
import com.epam.polinakrukovich.worldvision.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Connects Command and Data Access layers for performing operations
 * with Download instances.
 */
public class DownloadService {
    Logger logger = LogManager.getLogger(getClass());

    public void createDownload(String userId, String imageUrl) throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        DownloadDao dao = factory.getDownloadDao();
        try {
            dao.createDownload(imageUrl, userId);
        } catch (DaoException e) {
            logger.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }
}
