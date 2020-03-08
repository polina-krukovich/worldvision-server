package com.epam.polinakrukovich.worldvision.dao.factory;

import com.epam.polinakrukovich.worldvision.dao.DownloadDao;
import com.epam.polinakrukovich.worldvision.dao.ImageDao;
import com.epam.polinakrukovich.worldvision.dao.LikeDao;
import com.epam.polinakrukovich.worldvision.dao.UserDao;
import com.epam.polinakrukovich.worldvision.dao.impl.SqlDownloadDao;
import com.epam.polinakrukovich.worldvision.dao.impl.SqlImageDao;
import com.epam.polinakrukovich.worldvision.dao.impl.SqlLikeDao;
import com.epam.polinakrukovich.worldvision.dao.impl.SqlUserDao;

public class DaoFactory {
    private static final class SingletonHolder {
        private static final DaoFactory instance = new DaoFactory();
    }

    private final UserDao sqlUserDao;
    private final ImageDao sqlImageDao;
    private final LikeDao sqlLikeDao;
    private final DownloadDao sqlDownloadDao;

    private DaoFactory() {
        sqlUserDao = new SqlUserDao();
        sqlImageDao = new SqlImageDao();
        sqlLikeDao = new SqlLikeDao();
        sqlDownloadDao = new SqlDownloadDao();
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

    public LikeDao getLikeDao() {
        return sqlLikeDao;
    }

    public DownloadDao getDownloadDao() {
        return sqlDownloadDao;
    }
}
