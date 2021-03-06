package com.epam.polinakrukovich.worldvision.dao.impl;

import com.epam.polinakrukovich.worldvision.dao.ColorDao;
import com.epam.polinakrukovich.worldvision.dao.SqlDao;
import com.epam.polinakrukovich.worldvision.dao.exception.DaoException;
import com.epam.polinakrukovich.worldvision.entity.Color;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlColorDao extends SqlDao implements ColorDao {

    Logger logger = LogManager.getLogger(getClass());

    @Override
    public List<Color> readAllColors() throws DaoException {
        String query = "CALL SelectAllColors();";
        Connection connection = tryGetConnection();
        List<Color> colors = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int red = rs.getInt("red");
                int green = rs.getInt("green");
                int blue = rs.getInt("blue");
                Color color = new Color(id, red, green, blue);
                colors.add(color);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DaoException(e.getMessage());
        }
        releaseConnection(connection);
        return colors;
    }
}
