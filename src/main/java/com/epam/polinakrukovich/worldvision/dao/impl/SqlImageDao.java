package com.epam.polinakrukovich.worldvision.dao.impl;

import com.epam.polinakrukovich.worldvision.dao.ImageDao;
import com.epam.polinakrukovich.worldvision.dao.SqlDao;
import com.epam.polinakrukovich.worldvision.dao.exception.DaoException;
import com.epam.polinakrukovich.worldvision.entity.Image;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlImageDao extends SqlDao implements ImageDao {

    private Logger logger = LogManager.getLogger(getClass());

    @Override
    public void createImage(String url, String userId) throws DaoException {
        String query = "CALL CreateImage(?, ?);";
        Connection connection = tryGetConnection();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, url);
            ps.setString(2, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DaoException(e.getMessage());
        } finally {
            releaseConnection(connection);
        }
    }

    @Override
    public Image[] readImageByUser(String userId) throws DaoException {
        String query = "CALL SelectImageByUser(?);";
        Connection connection = tryGetConnection();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, userId);
            ResultSet rs = ps.executeQuery();
            return getImages(rs);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DaoException(e.getMessage());
        } finally {
            releaseConnection(connection);
        }

    }

    @Override
    public Image[] readImageTop() throws DaoException {
        String query = "CALL SelectImageTop();";
        Connection connection = tryGetConnection();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            return getImages(rs);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DaoException(e.getMessage());
        } finally {
            releaseConnection(connection);
        }
    }

    @Override
    public Image[] readImageByTag(String tag) throws DaoException {
        String query = "CALL SelectImageByTag(?);";
        Connection connection = tryGetConnection();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, tag);
            ResultSet rs = ps.executeQuery();
            return getImages(rs);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DaoException(e.getMessage());
        } finally {
            releaseConnection(connection);
        }
    }

    @Override
    public Image[] readImageByColor(int colorId) throws DaoException {
        String query = "CALL SelectImageByColor(?);";
        Connection connection = tryGetConnection();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, colorId);
            ResultSet rs = ps.executeQuery();
            return getImages(rs);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DaoException(e.getMessage());
        } finally {
            releaseConnection(connection);
        }
    }

    @Override
    public Image[] readImageByCreationTime(int daysPassed) throws DaoException {
        String query = "CALL SelectImageByCreationTime(?);";
        Connection connection = tryGetConnection();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, daysPassed);
            ResultSet rs = ps.executeQuery();
            return getImages(rs);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DaoException(e.getMessage());
        } finally {
            releaseConnection(connection);
        }
    }

    @Override
    public void deleteImage(String url) throws DaoException {
        String query = "CALL DeleteImage(?);";
        Connection connection = tryGetConnection();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, url);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DaoException(e.getMessage());
        } finally {
            releaseConnection(connection);
        }
    }

    private Image[] getImages(ResultSet rs) throws SQLException {
        List<Image> imageList = new ArrayList<>();
        while (rs.next()) {
            String url = rs.getString("url");
            String userId = rs.getString("user_id");
            int downloadsCount = rs.getInt("downloads_count");
            Timestamp timestamp = rs.getTimestamp("creation_time");
            DateTime creationTime = DateTime.parse(timestamp.toLocalDateTime().toString());
            String tags = rs.getString("tags");
            Image image = new Image(url, userId, downloadsCount, creationTime, tags);
            imageList.add(image);
        }
        Image[] images = new Image[imageList.size()];
        imageList.toArray(images);
        return images;
    }
}
