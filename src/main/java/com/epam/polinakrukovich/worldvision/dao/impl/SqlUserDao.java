package com.epam.polinakrukovich.worldvision.dao.impl;

import com.epam.polinakrukovich.worldvision.dao.SqlDao;
import com.epam.polinakrukovich.worldvision.dao.UserDao;
import com.epam.polinakrukovich.worldvision.dao.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlUserDao extends SqlDao implements UserDao {

    private Logger logger = LogManager.getLogger(getClass());

    @Override
    public void createUser(String userId) throws DaoException {
        String query = "CALL CreateUser(?);";
        Connection connection = tryGetConnection();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DaoException(e.getMessage());
        }
        releaseConnection(connection);
    }

    @Override
    public void deleteUser(String userId) throws DaoException {
        String query = "CALL DeleteUser(?);";
        Connection connection = tryGetConnection();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DaoException(e.getMessage());
        }
        releaseConnection(connection);
    }
}
