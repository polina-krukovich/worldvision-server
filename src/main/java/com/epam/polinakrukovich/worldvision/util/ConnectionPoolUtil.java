package com.epam.polinakrukovich.worldvision.util;

import com.epam.polinakrukovich.worldvision.config.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * {@link ConnectionPoolUtil} class provides the minimal functionality expected from
 * a typical connection pooling implementation. The class initializes a connection
 * pool based on an ArrayList that stores 10 connections, which can be easily reused.
 *
 * Once the pool is created, connections are fetched from the pool, so there's no need
 * to create new ones. When a connection is released, it's actually returned back to
 * the pool, so other clients can reuse it.
 *
 * @see DriverManager
 * @see Connection
 *
 * @author Polina Krukovich
 */
public class ConnectionPoolUtil {
    private static final class SingletonHolder {
        private static final ConnectionPoolUtil instance = new ConnectionPoolUtil();
    }

    private final Logger logger = LogManager.getLogger();
    private final int INITIAL_POOL_SIZE = 10;
    private List<Connection> connectionPool = new ArrayList<>();
    private List<Connection> usedConnections = new ArrayList<>();
    private String dbUrl = "";
    private String dbUser = "";
    private String dbPassword = "";

    public static ConnectionPoolUtil getInstance() {
        return SingletonHolder.instance;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public String getDbUser() {
        return dbUser;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    /**
     * Constructs and initializes the {@link ConnectionPoolUtil} object based on
     * configuration properties stored in "config.properties" file.
     */
    private ConnectionPoolUtil() {
        File configFile = new File(Config.CONFIG_FILE_NAME);
        try (FileReader reader = new FileReader(configFile)) {
            Properties props = new Properties();
            props.load(reader);
            dbUrl = props.getProperty("dbUrl");
            dbPassword = props.getProperty("dbPassword");
            dbUser = props.getProperty("dbUser");
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            try {
                connectionPool.add(DriverManager.getConnection(dbUrl, dbUser, dbPassword));
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
    }

    /**
     * Retrieves pooled connection. If pool is empty, creates a new one.
     *
     * @return {@link Connection} object.
     * @throws SQLException if creation of a new connection using
     * {@link DriverManager#getConnection(String, String, String)} fails.
     */
    public Connection getConnection() throws SQLException {
        if (connectionPool.isEmpty()) {
            connectionPool.add(DriverManager.getConnection(dbUrl, dbUser, dbPassword));
        }
        Connection connection = connectionPool.remove(connectionPool.size() - 1);
        usedConnections.add(connection);
        return connection;
    }

    /**
     * Returns the specified connection back to the connection pool for
     * future use.
     *
     * @param connection {@link Connection} to release.
     */
    public void releaseConnection (Connection connection) {
        usedConnections.remove(connection);
        connectionPool.add(connection);
    }

    /**
     * Realizes all the used connections and closes connections from
     * connection pool.
     *
     * @throws SQLException if failed to close the connection.
     */
    public void shutdown() throws SQLException{
        usedConnections.forEach(this::releaseConnection);
        for (Connection connection : connectionPool) {
            connection.close();
        }
        connectionPool.clear();
    }
}
