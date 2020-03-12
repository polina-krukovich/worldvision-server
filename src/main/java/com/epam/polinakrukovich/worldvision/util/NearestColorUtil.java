package com.epam.polinakrukovich.worldvision.util;

import com.epam.polinakrukovich.worldvision.dao.ColorDao;
import com.epam.polinakrukovich.worldvision.dao.exception.DaoException;
import com.epam.polinakrukovich.worldvision.dao.factory.DaoFactory;
import com.epam.polinakrukovich.worldvision.entity.Color;
import com.google.cloud.vision.v1.ColorInfo;
import com.google.common.annotations.VisibleForTesting;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

/**
 * {@link NearestColorUtil} provides basic functionality for calculating
 * nearest color values.
 *
 * @see Color
 * @see ColorInfo
 *
 * @author Polina Krukovich
 */
public class NearestColorUtil {
    private static final class SingletonHolder {
        private static final NearestColorUtil instance = new NearestColorUtil();
    }

    private Logger logger = LogManager.getLogger(getClass());
    private List<Color> primaryColors;

    public static NearestColorUtil getInstance() {
        return SingletonHolder.instance;
    }

    @VisibleForTesting
    NearestColorUtil() {
        DaoFactory factory = DaoFactory.getInstance();
        ColorDao dao = factory.getColorDao();
        try {
            primaryColors = dao.readAllColors();
        } catch (DaoException e) {
            logger.error(e.getMessage());
        }
    }

    public Color getNearestColor(ColorInfo colorInfo) {
        double minDistance = Double.MAX_VALUE;
        Color nearestColor = primaryColors.get(0);
        for(Color color : primaryColors) {
            double distance = Math.sqrt(
                    Math.pow(color.getRed() - colorInfo.getColor().getRed(), 2) +
                    Math.pow(color.getGreen() - colorInfo.getColor().getGreen(), 2) +
                    Math.pow(color.getBlue() - colorInfo.getColor().getBlue(), 2)
            );
            if (distance < minDistance) {
                minDistance = distance;
                nearestColor = color;
            }
        }
        return nearestColor;
    }
}
