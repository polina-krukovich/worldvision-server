package com.epam.polinakrukovich.worldvision.util;

import com.epam.polinakrukovich.worldvision.config.Config;
import com.epam.polinakrukovich.worldvision.entity.User;
import com.epam.polinakrukovich.worldvision.util.exception.UtilException;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.common.annotations.VisibleForTesting;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.*;
import com.google.firebase.auth.UserRecord.CreateRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * {@link AuthUtil} class provides functionality for managing users using
 * Firebase Admin SDK.
 *
 * @see FirebaseApp
 * @see FirebaseAuth
 *
 * @author Polina Krukovich
 */
public class AuthUtil {
    private static final class SingletonHolder {
        private static final AuthUtil instance = new AuthUtil();
    }

    private Logger logger = LogManager.getLogger(getClass());

    @VisibleForTesting
    FirebaseApp firebaseApp;
    @VisibleForTesting
    FirebaseAuth firebaseAuth;

    public static AuthUtil getInstance() {
        return SingletonHolder.instance;
    }

    /**
     * Constructs {@link AuthUtil} object and initializes Admin SDK using
     * service account, creates Firebase App and Firebase Authentication
     * objects.
     */
    @VisibleForTesting
    AuthUtil() {
        Config config = Config.getInstance();
        String saFilePath = config.getSaFilePath();
        try (InputStream stream = Objects.requireNonNull(
                AuthUtil.class.getClassLoader().getResourceAsStream(saFilePath))) {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(stream))
                    .build();
            firebaseApp = FirebaseApp.initializeApp(options);
            firebaseAuth = FirebaseAuth.getInstance(firebaseApp);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * Verifies the integrity and authenticity of the ID token.
     *
     * @see FirebaseToken
     * @param idToken ID token retrieved on the client.
     * @return true if the provided ID token has the correct format,
     *         is not expired, and is properly signed.
     */
    public String verifyIdToken(String idToken) throws UtilException {
        try {
            FirebaseToken decodedToken = firebaseAuth.verifyIdToken(idToken);
            return decodedToken.getUid();
        } catch (FirebaseAuthException e) {
            logger.error(e.getMessage());
            throw new UtilException(e.getMessage());
        }
    }

    /**
     * Checks the existence of the user with provided userId.
     *
     * @param userId user ID
     * @return {@code true} if user exists,
     *         {@code false} otherwise.
     */
    public boolean checkIfUserExists(String userId) {
        try {
            return firebaseAuth.getUser(userId) != null;
        } catch (FirebaseAuthException | IllegalArgumentException e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    public User[] listUsers() throws UtilException {
        ListUsersPage page = null;
        try {
            page = FirebaseAuth.getInstance().listUsers(null);
        } catch (FirebaseAuthException e) {
            logger.error(e.getMessage());
            throw new UtilException(e.getMessage());
        }

        List<User> userList = new ArrayList<>();
        for (ExportedUserRecord userRecord : page.iterateAll()) {
            User user = new User();

            user.setUid(userRecord.getUid());
            user.setEmail(userRecord.getEmail());
            user.setDisplayName(userRecord.getDisplayName());
            user.setPhone(userRecord.getPhoneNumber());
            user.setPhotoUrl(userRecord.getPhotoUrl());
            user.setDisabled(userRecord.isDisabled());

            UserMetadata meta = userRecord.getUserMetadata();
            String format = "dd MMM YYYY HH:mm:ss";

            long creationTimestamp = meta.getCreationTimestamp();
            DateTime creationTime = new DateTime(creationTimestamp);
            user.setCreationTime(creationTime.toString(format));

            long lastSignInTimestamp = meta.getLastSignInTimestamp();
            DateTime lastSignInTime = new DateTime(lastSignInTimestamp);
            user.setLastSignInTime(lastSignInTime.toString(format));

            UserInfo[] providerData = userRecord.getProviderData();
            String[] providers = new String[userRecord.getProviderData().length];
            for (int i = 0; i < providerData.length; i++) {
                providers[i] = providerData[i].getProviderId();
            }
            user.setProviders(providers);

            userList.add(user);
        }

        User[] users = new User[userList.size()];
        userList.toArray(users);
        return users;
    }

    public String createUser(String email, String password) throws UtilException {
        CreateRequest request = new CreateRequest()
                .setEmail(email)
                .setPassword(password);
        try {
            UserRecord userRecord = firebaseAuth.createUser(request);
            return userRecord.getUid();
        } catch (FirebaseAuthException e) {
            logger.error(e.getMessage());
            throw new UtilException(e.getMessage());
        }
    }

    public void deleteUser(String userId) throws UtilException {
        try {
            firebaseAuth.deleteUser(userId);
        } catch (FirebaseAuthException e) {
            logger.error(e.getMessage());
            throw new UtilException(e.getMessage());
        }
    }
}
