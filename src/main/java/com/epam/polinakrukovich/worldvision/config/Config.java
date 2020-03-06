package com.epam.polinakrukovich.worldvision.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sun.rmi.runtime.Log;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private static final class SingletonHolder {
        private static final Config instance = new Config();
    }

    private final String CONFIG_FILE_NAME = "config.properties";
    private Logger logger = LogManager.getLogger(getClass());

    private String dbUrl;
    private String dbUser;
    private String dbPassword;
    private String storageBucketName;
    private String saFilePath;

    public static Config getInstance() {
        return SingletonHolder.instance;
    }

    private Config() {
        File configFile = new File(CONFIG_FILE_NAME);
        try (FileReader reader = new FileReader(configFile)) {
            Properties props = new Properties();
            props.load(reader);
            dbUrl = props.getProperty("dbUrl");
            dbUser = props.getProperty("dbUser");
            dbPassword = props.getProperty("dbPassword");
            storageBucketName = props.getProperty("storageBucketName");
            saFilePath = props.getProperty("saFilePath");
        } catch (IOException e) {
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

    public String getStorageBucketName() {
        return storageBucketName;
    }

    public String getSaFilePath() {
        return saFilePath;
    }
}
