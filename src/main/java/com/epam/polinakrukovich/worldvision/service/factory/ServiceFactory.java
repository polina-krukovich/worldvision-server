package com.epam.polinakrukovich.worldvision.service.factory;

import com.epam.polinakrukovich.worldvision.service.DownloadService;
import com.epam.polinakrukovich.worldvision.service.ImageService;
import com.epam.polinakrukovich.worldvision.service.UserService;

/**
 * This class is used to produce and give access to Service Layer classes.
 */
public class ServiceFactory {
    private static final class SingletonHolder {
        private static final ServiceFactory instance = new ServiceFactory();
    }

    private final UserService userService;
    private final ImageService imageService;
    private final DownloadService downloadService;

    public static ServiceFactory getInstance() {
        return SingletonHolder.instance;
    }

    private ServiceFactory() {
        userService = new UserService();
        imageService = new ImageService();
        downloadService = new DownloadService();
    }

    public UserService getUserService() {
        return userService;
    }

    public ImageService getImageService() {
        return imageService;
    }

    public DownloadService getDownloadService() {
        return downloadService;
    }
}
