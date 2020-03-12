package com.epam.polinakrukovich.worldvision.config;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ConfigTest {

    @Test
    public void testDbUrl_ExpectedNotNull() {
        Config config = new Config();
        assertNotNull(config.dbUrl);
    }

    @Test
    public void testDbUser_ExpectedNotNull() {
        Config config = new Config();
        assertNotNull(config.dbUser);
    }

    @Test
    public void testDbPassword_ExpectedNotNull() {
        Config config = new Config();
        assertNotNull(config.dbPassword);
    }

    @Test
    public void testGetDbConnectionPoolInitialSize_ExpectedNotZero() {
        Config config = new Config();
        assertNotEquals(config.dbConnectionPoolInitialSize, 0);
    }

    @Test
    public void testGetStorageBucketName_ExpectedNotNull() {
        Config config = new Config();
        assertNotNull(config.storageBucketName);
    }

    @Test
    public void testGetSaFilePath_ExpectedNotNull() {
        Config config = new Config();
        assertNotNull(config.saFilePath);
    }
}