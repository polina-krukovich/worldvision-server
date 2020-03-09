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
        }
        releaseConnection(connection);
    }

    @Override
    public List<Image> readImageByUser(String userId, int pageIndex, int pageSize) throws DaoException {
        String query = "CALL SelectImageByUser(?, ?, ?);";
        Connection connection = tryGetConnection();
        List<Image> images;
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, userId);
            ps.setInt(2, pageIndex * pageSize);
            ps.setInt(3, pageSize);
            ResultSet rs = ps.executeQuery();
            images = getImages(rs);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DaoException(e.getMessage());
        }
        releaseConnection(connection);
        return images;
    }

    @Override
    public List<Image> readImageTop(int pageIndex, int pageSize) throws DaoException {
        String query = "CALL SelectImageTop(?, ?);";
        Connection connection = tryGetConnection();
        List<Image> images;
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, pageIndex * pageSize);
            ps.setInt(2, pageSize);
            ResultSet rs = ps.executeQuery();
            images = getImages(rs);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DaoException(e.getMessage());
        }
        releaseConnection(connection);
        return images;
    }

    @Override
    public List<Image> readImageByTag(String tag, int pageIndex, int pageSize) throws DaoException {
        String query = "CALL SelectImageByTag(?, ?, ?);";
        Connection connection = tryGetConnection();
        List<Image> images;
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, tag);
            ps.setInt(2, pageIndex * pageSize);
            ps.setInt(3, pageSize);
            ResultSet rs = ps.executeQuery();
            images = getImages(rs);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DaoException(e.getMessage());
        }
        releaseConnection(connection);
        return images;
    }

    @Override
    public List<Image> readImageByColor(int colorId, int pageIndex, int pageSize) throws DaoException {
        String query = "CALL SelectImageByColor(?, ?, ?);";
        Connection connection = tryGetConnection();
        List<Image> images;
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, colorId);
            ps.setInt(2, pageIndex * pageSize);
            ps.setInt(3, pageSize);
            ResultSet rs = ps.executeQuery();
            images = getImages(rs);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DaoException(e.getMessage());
        }
        releaseConnection(connection);
        return images;
    }

    @Override
    public List<Image> readImageByCreationTime(int daysPassed, int pageIndex, int pageSize) throws DaoException {
        String query = "CALL SelectImageByCreationTime(?, ?, ?);";
        Connection connection = tryGetConnection();
        List<Image> images;
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, daysPassed);
            ps.setInt(2, pageIndex * pageSize);
            ps.setInt(3, pageSize);
            ResultSet rs = ps.executeQuery();
            images = getImages(rs);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DaoException(e.getMessage());
        }
        releaseConnection(connection);
        return images;
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
        }
        releaseConnection(connection);
    }

    private List<Image> getImages(ResultSet rs) throws SQLException {
        List<Image> images = new ArrayList<>();
        while (rs.next()) {
            String url = rs.getString("url");
            String userId = rs.getString("user_id");
            int likesCount = rs.getInt("likes_count");
            int downloadsCount = rs.getInt("downloads_count");
            Timestamp timestamp = rs.getTimestamp("creation_time");
            DateTime creationTime = DateTime.parse(timestamp.toLocalDateTime().toString());
            Image image = new Image(userId, url, likesCount, downloadsCount, creationTime);
            images.add(image);
        }
        return images;
    }
}
