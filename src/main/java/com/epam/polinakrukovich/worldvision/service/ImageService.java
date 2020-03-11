package com.epam.polinakrukovich.worldvision.service;

import com.epam.polinakrukovich.worldvision.dao.ImageColorDao;
import com.epam.polinakrukovich.worldvision.dao.ImageDao;
import com.epam.polinakrukovich.worldvision.dao.ImageTagDao;
import com.epam.polinakrukovich.worldvision.dao.TagDao;
import com.epam.polinakrukovich.worldvision.dao.exception.DaoException;
import com.epam.polinakrukovich.worldvision.dao.factory.DaoFactory;
import com.epam.polinakrukovich.worldvision.entity.Color;
import com.epam.polinakrukovich.worldvision.entity.Image;
import com.epam.polinakrukovich.worldvision.service.exception.ServiceException;
import com.epam.polinakrukovich.worldvision.util.NearestColorUtil;
import com.epam.polinakrukovich.worldvision.util.StorageUtil;
import com.epam.polinakrukovich.worldvision.util.VisionUtil;
import com.epam.polinakrukovich.worldvision.util.exception.UtilException;
import com.google.cloud.vision.v1.ColorInfo;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javatuples.Pair;

import java.io.InputStream;
import java.util.*;

public class ImageService {
    Logger logger = LogManager.getLogger(getClass());

    public void createImage(String url, String gcsPath, String uid) throws ServiceException {
        saveImage(url, uid);
        Set<String> tags = generateTags(gcsPath);
        saveTags(url, tags);
        Map<Color, Double> colors = detectColors(gcsPath);
        saveColors(url, colors);
    }

    public Image[] listImagesByUser(String uid) throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        ImageDao dao = factory.getImageDao();
        try {
            return dao.readImageByUser(uid);
        } catch (DaoException e) {
            logger.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }

    private void saveImage(String imageUrl, String userId) throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        ImageDao dao = factory.getImageDao();
        try {
            dao.createImage(imageUrl, userId);
        } catch (DaoException e) {
            logger.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }

    private Set<String> generateTags(String gcsPath) {
        VisionUtil visionUtil = VisionUtil.getInstance();
        Set<String> tags = new HashSet<>();
        try {
            tags = visionUtil.generateTags(gcsPath);
        } catch (UtilException e) {
            logger.error(e.getMessage());
        }
        return tags;
    }

    private Map<Color, Double> detectColors(String gcsPath) {
        Map<Color, Double> primaryColors = new HashMap<>();
        try {
            VisionUtil visionUtil = VisionUtil.getInstance();
            List<ColorInfo> colorInfos = visionUtil.detectColors(gcsPath);
            NearestColorUtil nearestColorUtil = NearestColorUtil.getInstance();
            for (ColorInfo colorInfo : colorInfos) {
                Color nearestColor = nearestColorUtil.getNearestColor(colorInfo);
                if (primaryColors.containsKey(nearestColor)) {
                    double newFraction = primaryColors.get(nearestColor) + colorInfo.getPixelFraction();
                    primaryColors.replace(nearestColor, newFraction);
                } else {
                    primaryColors.put(nearestColor, (double) colorInfo.getPixelFraction());
                }
            }
        } catch (UtilException e) {
            logger.error(e.getMessage());
        }
        return primaryColors;
    }

    private void saveTags(String imageUrl, Set<String> tags) {
        DaoFactory factory = DaoFactory.getInstance();
        TagDao tagDao = factory.getTagDao();
        ImageTagDao imageTagDao = factory.getImageTagDao();
        for (String tag : tags) {
            try {
                tagDao.createTag(tag);
                imageTagDao.createImageTag(imageUrl, tag);
            } catch (DaoException e) {
                logger.error(e.getMessage());
            }
        }
    }

    private void saveColors(String imageUrl, Map<Color, Double> colors) {
        DaoFactory factory = DaoFactory.getInstance();
        ImageColorDao dao = factory.getImageColorDao();
        for (Map.Entry<Color, Double> color : colors.entrySet()) {
            try {
                dao.createImageColor(imageUrl, color.getKey().getId(), color.getValue());
            } catch (DaoException e) {
                logger.error(e.getMessage());
            }
        }
    }

}
