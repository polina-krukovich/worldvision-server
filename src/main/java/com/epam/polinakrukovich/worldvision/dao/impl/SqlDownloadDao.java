package com.epam.polinakrukovich.worldvision.dao.impl;

import com.epam.polinakrukovich.worldvision.dao.DownloadDao;
import com.epam.polinakrukovich.worldvision.dao.SqlDao;
import com.epam.polinakrukovich.worldvision.dao.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlDownloadDao extends SqlDao implements DownloadDao {

    Logger logger = LogManager.getLogger(getClass());

    @Override
    public void createDownload(String imageUrl, String userId) throws DaoException {
        String query = "CALL CreateDownload(?, ?);";
        Connection connection = tryGetConnection();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, imageUrl);
            ps.setString(2, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DaoException(e.getMessage());
        }
        releaseConnection(connection);
    }

    @Override
    public int readDownloadsCountByCreationTime(int daysPassed) throws DaoException {
        String query = "CALL SelectDownloadsCountByCreationTime(?);";
        Connection connection = tryGetConnection();
        int downloadsCount = 0;
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, daysPassed);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                downloadsCount = rs.getInt("COUNT(id)");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DaoException(e.getMessage());
        }
        releaseConnection(connection);
        return downloadsCount;
    }
}
