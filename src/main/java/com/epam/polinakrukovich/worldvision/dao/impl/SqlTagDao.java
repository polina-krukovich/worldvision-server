package com.epam.polinakrukovich.worldvision.dao.impl;

import com.epam.polinakrukovich.worldvision.dao.SqlDao;
import com.epam.polinakrukovich.worldvision.dao.TagDao;
import com.epam.polinakrukovich.worldvision.dao.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlTagDao extends SqlDao implements TagDao {
    Logger logger = LogManager.getLogger(getClass());

    @Override
    public void createTag(String tag) throws DaoException {
        String query = "CALL CreateTag(?);";
        Connection connection = tryGetConnection();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, tag);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DaoException(e.getMessage());
        }
        releaseConnection(connection);
    }
}
