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
    public void testAuthUtil_FirebaseAuthInitialization() {
        assertNotNull(authUtil.firebaseAuth);
    }
}