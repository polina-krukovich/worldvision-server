package com.epam.polinakrukovich.worldvision.config;

import com.google.common.annotations.VisibleForTesting;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * {@link Config} class stores application configuration data.
 * This is a Singleton class, the first time it is called, data
 * is loaded from configuration file.
 *
 * @see Properties
 *
 * @author Polina Krukovich
 */
public class Config {
    private static final class SingletonHolder {
        private static final Config instance = new Config();
    }

    /** Application configuration file name. */
    private final String CONFIG_FILE_NAME = "config.properties";
    private Logger logger = LogManager.getLogger(getClass());

    @VisibleForTesting
    String dbUrl;
    @VisibleForTesting
    String dbUser;
    @VisibleForTesting
    String dbPassword;
    @VisibleForTesting
    int dbConnectionPoolInitialSize;
    @VisibleForTesting
    String storageBucketName;
    @VisibleForTesting
    String saFilePath;

    public static Config getInstance() {
        return SingletonHolder.instance;
    }

    /**
     * Constructs the object of {@link  Config}, reads the
     * configuration file and saves the properties.
     */
    @VisibleForTesting
     Config() {
        try (InputStream stream = getClass().getClassLoader()
                .getResourceAsStream(CONFIG_FILE_NAME)) {
            Properties props = new Properties();
            props.load(stream);
            dbUrl = props.getProperty("dbUrl");
            dbUser = props.getProperty("dbUser");
            dbPassword = props.getProperty("dbPassword");
            dbConnectionPoolInitialSize = Integer.parseInt(props.getProperty("dbConnectionPoolInitialSize"));
            storageBucketName = props.getProperty("storageBucketName");
            saFilePath = props.getProperty("saFilePath");
        } catch (IOException | NullPointerException e) {
            logger.error(e.getMessage());
        }
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public String getDbUser() {
        return dbUser;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public int getDbConnectionPoolInitialSize() {
        return dbConnectionPoolInitialSize;
    }

    public String getStorageBucketName() {
        return storageBucketName;
    }

    public String getSaFilePath() {
        return saFilePath;
    }
}
