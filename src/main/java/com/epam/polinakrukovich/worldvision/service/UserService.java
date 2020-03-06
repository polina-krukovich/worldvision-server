package com.epam.polinakrukovich.worldvision.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserService {
    private static final class SingletonHolder {
        private static final UserService instance = new UserService();
    }

    private final Logger logger = LogManager.getLogger(getClass());

    public static UserService getInstance() {
        return SingletonHolder.instance;
    }

    private UserService() {

    }
}
