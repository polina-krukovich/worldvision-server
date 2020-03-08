package com.epam.polinakrukovich.worldvision.dao;

import com.epam.polinakrukovich.worldvision.entity.Image;
import org.joda.time.DateTime;

import java.util.List;

public interface ImageDao {
    void createImage(Image image);
    List<Image> listImageByUser(int userId);
    List<Image> listImageTop();
    List<Image> listImageByQuery(int colorId, List<String> tags, DateTime startTime);
    void deleteImage(int id);
}
