package com.epam.polinakrukovich.worldvision.service;

import com.epam.polinakrukovich.worldvision.dao.UserDao;
import com.epam.polinakrukovich.worldvision.dao.exception.DaoException;
import com.epam.polinakrukovich.worldvision.dao.factory.DaoFactory;
import com.epam.polinakrukovich.worldvision.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Connects Command and Data Access layers for performing operations
 * with User instances.
 */
public class UserService {
    Logger logger = LogManager.getLogger(getClass());

    public void createUser(String userId) throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        UserDao dao = factory.getUserDao();
        try {
            dao.createUser(userId);
        } catch (DaoException e) {
            logger.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }

    public void deleteUser(String userId) throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        UserDao dao = factory.getUserDao();
        try {
            dao.deleteUser(userId);
        } catch (DaoException e) {
            logger.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }

}
