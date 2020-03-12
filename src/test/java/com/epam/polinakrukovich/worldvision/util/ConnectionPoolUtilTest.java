package com.epam.polinakrukovich.worldvision.util;

import com.epam.polinakrukovich.worldvision.config.Config;
import com.epam.polinakrukovich.worldvision.util.exception.UtilException;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;

public class ConnectionPoolUtilTest {

    @Test
    public void testGetInstance_WhenMultipleThreadsGetSingletonInstance_ExpectedOneInstanceCreated() throws InterruptedException {
        int threadsAmount = 50;
        Set<ConnectionPoolUtil> singletonSet = new HashSet<>();
        singletonSet.add(ConnectionPoolUtil.getInstance());
        ExecutorService executorService = Executors.newFixedThreadPool(threadsAmount);

        for (int i = 0; i < threadsAmount; i++) {
            executorService.execute(() -> {
                ConnectionPoolUtil singleton = ConnectionPoolUtil.getInstance();
                singletonSet.add(singleton);
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
        // Given
        int expected = 1;
        // When
        int actual = singletonSet.size();
        // Then
        assertEquals(actual, expected);

    }

    @Test
    public void testGetDbUrl_ExpectedNotNull() {
        ConnectionPoolUtil pool = new ConnectionPoolUtil();
        assertNotNull(pool.getDbUrl());
    }

    @Test
    public void testGetDbUrl_ExpectedNotEmpty() {
        ConnectionPoolUtil pool = new ConnectionPoolUtil();
        assertFalse(pool.getDbUrl().isEmpty());
    }

    @Test
    public void testGetDbUser_ExpectedNotNull() {
        ConnectionPoolUtil pool = new ConnectionPoolUtil();
        assertNotNull(pool.getDbUser());
    }

    @Test
    public void testGetDbUser_ExpectedNotEmpty() {
        ConnectionPoolUtil pool = new ConnectionPoolUtil();
        assertFalse(pool.getDbUser().isEmpty());
    }

    @Test
    public void testGetDbPassword_ExpectedNotNull() {
        ConnectionPoolUtil pool = new ConnectionPoolUtil();
        assertNotNull(pool.getDbPassword());
    }

    @Test
    public void testGetDbPassword_ExpectedNotEmpty() {
        ConnectionPoolUtil pool = new ConnectionPoolUtil();
        assertFalse(pool.getDbPassword().isEmpty());
    }

    @Test
    public void testGetConnection_ExpectedNotNull() throws UtilException {
        ConnectionPoolUtil pool = new ConnectionPoolUtil();
        // When
        Connection actual = pool.getConnection();
        // Then
        assertNotNull(actual);
    }

    @Test
    public void testGetConnection_WhenMultipleThreadsCall_ExpectedEqualThreadsAndConnectionsCount() throws InterruptedException {
        ConnectionPoolUtil pool = new ConnectionPoolUtil();
        int threadsAmount = 30;
        ExecutorService executorService = Executors.newFixedThreadPool(threadsAmount);

        for (int i = 0; i < threadsAmount; i++) {
            executorService.execute(() -> {
                try {
                    pool.getConnection();
                } catch (UtilException e) {
                    e.printStackTrace();
                }
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
        // When
        int actual = pool.usedConnections.size();
        // Then
        assertEquals(actual, threadsAmount);
    }

    @Test
    public void testReleaseConnection_WhenGetAndReleaseConnection_ExpectedZeroUsedConnections() throws UtilException {
        ConnectionPoolUtil pool = new ConnectionPoolUtil();
        Connection connection = pool.getConnection();
        pool.releaseConnection(connection);
        // Given
        int expected = 0;
        // When
        int actual = pool.usedConnections.size();
        // Then
        assertEquals(actual, expected);
    }
}