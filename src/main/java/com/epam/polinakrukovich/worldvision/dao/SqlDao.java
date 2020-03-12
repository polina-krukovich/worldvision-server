package com.epam.polinakrukovich.worldvision.dao;

import com.epam.polinakrukovich.worldvision.dao.exception.DaoException;
import com.epam.polinakrukovich.worldvision.util.ConnectionPoolUtil;
import com.epam.polinakrukovich.worldvision.util.exception.UtilException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;

public abstract class SqlDao {
    private final Logger logger = LogManager.getLogger(getClass());
    private ConnectionPoolUtil pool = ConnectionPoolUtil.getInstance();

    protected Connection tryGetConnection() throws DaoException {
        Connection connection;
        try {
            connection = pool.getConnection();
        } catch (UtilException e) {
            logger.error(e.getMessage());
            throw new DaoException(e.getMessage());
        }
        return connection;
    }

    protected void releaseConnection(Connection connection) {
        pool.releaseConnection(connection);
    }
}
