package com.epam.polinakrukovich.worldvision.dao.factory;

import com.epam.polinakrukovich.worldvision.dao.*;
import com.epam.polinakrukovich.worldvision.dao.impl.*;

/**
 * This factory class is used for creating and accessing
 * DAO instances.
 *
 * @author Polina Krukovich
 */
public class DaoFactory {
    private static final class SingletonHolder {
        private static final DaoFactory instance = new DaoFactory();
    }

    private final UserDao sqlUserDao;
    private final ImageDao sqlImageDao;
    private final DownloadDao sqlDownloadDao;
    private final TagDao sqlTagDao;
    private final ImageTagDao sqlImageTagDao;
    private final ColorDao sqlColorDao;
    private final ImageColorDao sqlImageColorDao;

    private DaoFactory() {
        sqlUserDao = new SqlUserDao();
        sqlImageDao = new SqlImageDao();
        sqlDownloadDao = new SqlDownloadDao();
        sqlTagDao = new SqlTagDao();
        sqlImageTagDao = new SqlImageTagDao();
        sqlColorDao = new SqlColorDao();
        sqlImageColorDao = new SqlImageColorDao();
    }

    public static DaoFactory getInstance() {
        return SingletonHolder.instance;
    }

    public UserDao getUserDao() {
        return sqlUserDao;
    }

    public ImageDao getImageDao() {
        return sqlImageDao;
    }

    public DownloadDao getDownloadDao() {
        return sqlDownloadDao;
    }

    public TagDao getTagDao() {
        return sqlTagDao;
    }

    public ImageTagDao getImageTagDao() {
        return sqlImageTagDao;
    }

    public ColorDao getColorDao() {
        return sqlColorDao;
    }

    public ImageColorDao getImageColorDao() {
        return sqlImageColorDao;
    }
}
