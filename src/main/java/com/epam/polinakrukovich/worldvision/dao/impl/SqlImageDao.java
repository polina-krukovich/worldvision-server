package com.epam.polinakrukovich.worldvision.dao.impl;

import com.epam.polinakrukovich.worldvision.dao.ImageDao;
import com.epam.polinakrukovich.worldvision.dao.SqlDao;
import com.epam.polinakrukovich.worldvision.entity.Image;
import org.joda.time.DateTime;

import java.util.List;

public class SqlImageDao extends SqlDao implements ImageDao {
    @Override
    public void createImage(Image image) {

    }

    @Override
    public List<Image> listImageByUser(int userId) {
        return null;
    }

    @Override
    public List<Image> listImageTop() {
        return null;
    }

    @Override
    public List<Image> listImageByQuery(int colorId, List<String> tags, DateTime startTime) {
        return null;
    }

    @Override
    public void deleteImage(int id) {

    }
}
