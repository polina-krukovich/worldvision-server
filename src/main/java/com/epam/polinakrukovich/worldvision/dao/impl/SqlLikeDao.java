package com.epam.polinakrukovich.worldvision.dao.impl;

import com.epam.polinakrukovich.worldvision.dao.LikeDao;
import com.epam.polinakrukovich.worldvision.dao.SqlDao;
import com.epam.polinakrukovich.worldvision.dao.exception.DaoException;
import com.epam.polinakrukovich.worldvision.entity.Like;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlLikeDao extends SqlDao implements LikeDao {

    Logger logger = LogManager.getLogger(getClass());

    @Override
    public void createLike(String imageUrl, String userId) throws DaoException {
        String query = "CALL CreateLike(?, ?);";
        executeUpdate(query, imageUrl, userId);
    }

    @Override
    public void deleteLike(String imageUrl, String userId) throws DaoException {
        String query = "CALL DeleteLike(?, ?);";
        executeUpdate(query, imageUrl, userId);
    }

    @Override
    public int readLikesCountByCreationTime(int daysPassed) throws DaoException {
        String query = "CALL SelectLikesCountByCreationTime(?);";
        Connection connection = tryGetConnection();
        int likesCount = 0;
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, daysPassed);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                likesCount = rs.getInt("COUNT(creation_time)");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DaoException(e.getMessage());
        }
        releaseConnection(connection);
        return likesCount;
    }

    @Override
    public List<Like> readLikesByImage(String imageUrl) throws DaoException {
        String query = "CALL SelectLikesByImage(?);";
        Connection connection = tryGetConnection();
        List<Like> likes = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, imageUrl);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String userId = rs.getString("user_id");
                Timestamp timestamp = rs.getTimestamp("creation_time");
                DateTime creationTime = DateTime.parse(timestamp.toLocalDateTime().toString());
                Like like = new Like(imageUrl, userId, creationTime);
                likes.add(like);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DaoException(e.getMessage());
        }
        releaseConnection(connection);
        return likes;
    }

    private void executeUpdate(String query, String imageUrl, String userId) throws DaoException {
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
}
