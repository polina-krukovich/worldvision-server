package com.epam.polinakrukovich.worldvision.util;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class StorageUtilTest {

    @Test
    public void testStorageUtil_ExpectedStorageNotNul() {
        StorageUtil util = new StorageUtil();
        assertNotNull(util.storage);
    }

}