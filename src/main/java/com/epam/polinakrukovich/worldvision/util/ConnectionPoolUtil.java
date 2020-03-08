package com.epam.polinakrukovich.worldvision.util;

import com.epam.polinakrukovich.worldvision.config.Config;
import com.epam.polinakrukovich.worldvision.util.exception.UtilException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

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
    private final ReentrantLock lock = new ReentrantLock();
    private List<Connection> connectionPool = new ArrayList<>();
    private List<Connection> usedConnections = new ArrayList<>();
    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    /**
     * Constructs and initializes the {@link ConnectionPoolUtil} object
     * based on configuration properties stored in {@link Config}.
     */
    private ConnectionPoolUtil() {
        Config config = Config.getInstance();
        dbUrl = config.getDbUrl();
        dbUser = config.getDbUser();
        dbPassword = config.getDbPassword();
        int initialPoolSize = config.getDbConnectionPoolInitialSize();
        for (int i = 0; i < initialPoolSize; i++) {
            try {
                connectionPool.add(DriverManager.getConnection(dbUrl, dbUser, dbPassword));
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
    }

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
     * Retrieves pooled connection. If pool is empty, creates a new one.
     *
     * @return {@link Connection} object.
     * @throws UtilException if creation of a new connection using
     * {@link DriverManager#getConnection(String, String, String)} fails.
     */
    public Connection getConnection() throws UtilException {
        lock.lock();
        try {
            if (connectionPool.isEmpty()) {
                connectionPool.add(DriverManager.getConnection(dbUrl, dbUser, dbPassword));
            }
            Connection connection = connectionPool.remove(connectionPool.size() - 1);
            usedConnections.add(connection);
            return connection;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new UtilException(e.getMessage());
        } finally {
            lock.unlock();
        }
    }

    /**
     * Returns the specified connection back to the connection pool for
     * future use.
     *
     * @param connection {@link Connection} to release.
     */
    public void releaseConnection (Connection connection) {
        lock.lock();
        try {
            usedConnections.remove(connection);
            connectionPool.add(connection);
        } finally {
            lock.unlock();
        }
    }

    /**
     * Realizes all the used connections and closes connections from
     * connection pool.
     *
     * @throws UtilException if failed to close the connection.
     */
    public void shutdown() throws UtilException {
        lock.lock();
        try {
            usedConnections.forEach(this::releaseConnection);
            for (Connection connection : connectionPool) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.error(e.getMessage());
                    throw new UtilException(e.getMessage());
                }
            }
            connectionPool.clear();
        } finally {
            lock.unlock();
        }
    }
}
