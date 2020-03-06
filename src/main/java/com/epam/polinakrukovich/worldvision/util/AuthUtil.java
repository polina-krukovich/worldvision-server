package com.epam.polinakrukovich.worldvision.util;

import com.epam.polinakrukovich.worldvision.config.Config;
import com.epam.polinakrukovich.worldvision.util.exception.UtilException;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.common.annotations.VisibleForTesting;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

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
        File saFile = new File(config.getSaFilePath());
        try (FileInputStream stream = new FileInputStream(saFile)) {
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
}
