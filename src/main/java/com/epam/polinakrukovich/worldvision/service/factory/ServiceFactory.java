package com.epam.polinakrukovich.worldvision.service.factory;

import com.epam.polinakrukovich.worldvision.service.DownloadService;
import com.epam.polinakrukovich.worldvision.service.ImageService;
import com.epam.polinakrukovich.worldvision.service.LikeService;
import com.epam.polinakrukovich.worldvision.service.UserService;

public class ServiceFactory {
    private static final class SingletonHolder {
        private static final ServiceFactory instance = new ServiceFactory();
    }

    private final UserService userService;
    private final ImageService imageService;
    private final LikeService likeService;
    private final DownloadService downloadService;

    public static ServiceFactory getInstance() {
        return SingletonHolder.instance;
    }

    private ServiceFactory() {
        userService = new UserService();
        imageService = new ImageService();
        likeService = new LikeService();
        downloadService = new DownloadService();
    }

    public UserService getUserService() {
        return userService;
    }

    public ImageService getImageService() {
        return imageService;
    }

    public LikeService getLikeService() {
        return likeService;
    }

    public DownloadService getDownloadService() {
        return downloadService;
    }
}
