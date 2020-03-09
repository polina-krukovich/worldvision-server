package com.epam.polinakrukovich.worldvision.dao.impl;

import com.epam.polinakrukovich.worldvision.dao.SqlDao;
import com.epam.polinakrukovich.worldvision.dao.UserDao;
import com.epam.polinakrukovich.worldvision.dao.exception.DaoException;
import com.epam.polinakrukovich.worldvision.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    public boolean checkUser(String userId) throws DaoException {
        String query = "CALL SelectUserCountById(?);";
        Connection connection = tryGetConnection();
        boolean userExists = false;
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, userId);
            ResultSet rs = ps.executeQuery();
            userExists = rs.next() && rs.getInt("COUNT(id)") == 1;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DaoException(e.getMessage());
        }
        releaseConnection(connection);
        return userExists;
    }

    @Override
    public List<User> readUsersLikedImage(String imageUrl) throws DaoException {
        String query = "CALL SelectUsersLikedImage(?);";
        Connection connection = tryGetConnection();
        List<User> users = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, imageUrl);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String userId = rs.getString("user_id");
                User user = new User(userId);
                users.add(user);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DaoException(e.getMessage());
        }
        releaseConnection(connection);
        return users;
    }

    @Override
    public List<User> readUsersDownloadedImage(String imageUrl) throws DaoException {
        String query = "CALL SelectUsersDownloadedImage(?);";
        Connection connection = tryGetConnection();
        List<User> users = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, imageUrl);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String userId = rs.getString("user_id");
                User user = new User(userId);
                users.add(user);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DaoException(e.getMessage());
        }
        releaseConnection(connection);
        return users;
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
