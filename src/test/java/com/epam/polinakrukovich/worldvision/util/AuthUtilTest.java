package com.epam.polinakrukovich.worldvision.util;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class AuthUtilTest {

    @Test
    public void testAuthUtil_FirebaseAppInitialization() {
        assertNotEquals(new AuthUtil().firebaseApp, null);
    }

    @Test
    public void testAuthUtil_FirebaseAuthInitialization() {
        assertNotEquals(new AuthUtil().firebaseAuth, null);
    }
}