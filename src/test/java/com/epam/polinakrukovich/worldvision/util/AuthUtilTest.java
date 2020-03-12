package com.epam.polinakrukovich.worldvision.util;

import com.google.firebase.auth.ExportedUserRecord;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.ListUsersPage;
import org.joda.time.DateTime;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class AuthUtilTest {
    @Test
    public void testAuthUtil_FirebaseAppInitialization() {
        AuthUtil authUtil = new AuthUtil();
        assertNotNull(authUtil.firebaseApp);
    }

    @Test
    public void testCheckIfUserExists_WhenNullPassed_ExpectedFalse() {
        AuthUtil authUtil = new AuthUtil();
        boolean actual = authUtil.checkIfUserExists(null);
        assertFalse(actual);
    }

    @Test
    public void testCheckIfUserExists_WhenEmptyStringPassed_ExpectedFalse() {
        AuthUtil authUtil = new AuthUtil();
        boolean actual = authUtil.checkIfUserExists("");
        assertFalse(actual);
    }

    @Test
    public void testCheckIfUserExists_WhenFalsyValuePassed_ExpectedFalse() {
        AuthUtil authUtil = new AuthUtil();
        boolean actual = authUtil.checkIfUserExists("123");
        assertFalse(actual);
    }
}