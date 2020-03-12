package com.epam.polinakrukovich.worldvision.dao.impl;

import com.epam.polinakrukovich.worldvision.dao.ImageColorDao;
import com.epam.polinakrukovich.worldvision.dao.SqlDao;
import com.epam.polinakrukovich.worldvision.dao.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlImageColorDao extends SqlDao implements ImageColorDao {
    Logger logger = LogManager.getLogger(getClass());

    @Override
    public void createImageColor(String imageUrl, int colorId, double score) throws DaoException {
        String query = "CALL CreateImageColor(?, ?, ?);";
        Connection connection = tryGetConnection();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, imageUrl);
            ps.setInt(2, colorId);
            ps.setDouble(3, score);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DaoException(e.getMessage());
        }
        releaseConnection(connection);
    }
}
