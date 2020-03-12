package com.epam.polinakrukovich.worldvision.util;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class AuthUtilTest {
    private AuthUtil authUtil = new AuthUtil();
    @Test
    public void testAuthUtil_FirebaseAppInitialization() {
        assertNotNull(authUtil.firebaseApp);
    }

    @Test
    public void testCheckIfUserExists_WhenNullPassed_ExpectedFalse() {
        boolean actual = authUtil.checkIfUserExists(null);
        assertFalse(actual);
    }

    @Test
    public void testCheckIfUserExists_WhenEmptyStringPassed_ExpectedFalse() {
        boolean actual = authUtil.checkIfUserExists("");
        assertFalse(actual);
    }

    @Test
    public void testCheckIfUserExists_WhenFalsyValuePassed_ExpectedFalse() {
        boolean actual = authUtil.checkIfUserExists("123");
        assertFalse(actual);
    }
}