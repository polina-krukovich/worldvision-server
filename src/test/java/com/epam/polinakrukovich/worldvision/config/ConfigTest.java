package com.epam.polinakrukovich.worldvision.config;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ConfigTest {

    @Test
    public void testGetDbUrl_ExpectedNotNull() {
        assertNotNull(new Config().getDbUrl());
    }

    @Test
    public void testGetDbUser_ExpectedNotNull() {
        assertNotNull(new Config().getDbUser());
    }

    @Test
    public void testGetDbPassword_ExpectedNotNull() {
        assertNotNull(new Config().getDbPassword());
    }

    @Test
    public void testGetDbConnectionPoolInitialSize_ExpectedNotZero() {
        assertNotEquals(new Config().getDbConnectionPoolInitialSize(), 0);
    }

    @Test
    public void testGetDbConnectionPoolMaxSize_ExpectedNotZero() {
        assertNotEquals(new Config().getDbConnectionPoolMaxSize(), 0);
    }

    @Test
    public void testGetStorageBucketName_ExpectedNotNull() {
        assertNotNull(new Config().getStorageBucketName());
    }

    @Test
    public void testGetSaFilePath_ExpectedNotNull() {
        assertNotNull(new Config().getSaFilePath());
    }
}