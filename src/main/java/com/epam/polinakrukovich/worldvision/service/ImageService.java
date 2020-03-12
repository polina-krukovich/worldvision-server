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
import com.epam.polinakrukovich.worldvision.util.TranslateUtil;
import com.epam.polinakrukovich.worldvision.util.VisionUtil;
import com.epam.polinakrukovich.worldvision.util.exception.UtilException;
import com.google.cloud.vision.v1.ColorInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.*;

/**
 * Connects Command and Data Access layers for performing operations
 * with Image instances. Uses Utils, such as {@link VisionUtil},
 * {@link NearestColorUtil}, {@link TranslateUtil} for image processing.
 */
public class ImageService {
    Logger logger = LogManager.getLogger(getClass());

    /**
     * Detects image tags and colors, uses DAO to save image data.
     * @param url image URL.
     * @param gcsPath Google Cloud Storage Path.
     * @param userId user ID.
     * @throws ServiceException
     */
    public void createImage(String url, String gcsPath, String userId) throws ServiceException {
        saveImage(url, userId);
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

    public Image[] listTopImages() throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        ImageDao dao = factory.getImageDao();
        try {
            return dao.readImageTop();
        } catch (DaoException e) {
            logger.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * Retrieves images that match specified tag.
     * P.S. tags are stored in English, so they are
     * translated if provided in another language.
     * @param tag tag.
     * @return images matching tag.
     * @throws ServiceException
     */
    public Image[] listImagesByTag(String tag) throws ServiceException {
        TranslateUtil util = TranslateUtil.getInstance();
        String lang = util.detectLanguage(tag);
        if (!"en".equals(lang)) {
            tag = util.translate(lang, "en", tag);
        }
        DaoFactory factory = DaoFactory.getInstance();
        ImageDao dao = factory.getImageDao();
        try {
            return dao.readImageByTag(tag);
        } catch (DaoException e) {
            logger.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }

    public Image[] listImagesByColor(int colorId) throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        ImageDao dao = factory.getImageDao();
        try {
            return dao.readImageByColor(colorId);
        } catch (DaoException e) {
            logger.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }

    public Image[] listImagesByCreationTime(int daysPassed) throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        ImageDao dao = factory.getImageDao();
        try {
            return dao.readImageByCreationTime(daysPassed);
        } catch (DaoException e) {
            logger.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }

    public void deleteImage(String imageUrl) throws ServiceException {
        DaoFactory factory = DaoFactory.getInstance();
        ImageDao dao = factory.getImageDao();
        try {
            dao.deleteImage(imageUrl);
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
