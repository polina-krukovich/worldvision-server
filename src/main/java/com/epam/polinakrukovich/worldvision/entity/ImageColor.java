package com.epam.polinakrukovich.worldvision.entity;

import java.util.Objects;

/**
 * Entity-class for storing relation between image and color.
 *
 * @author Polina Krukovich
 */
public class ImageColor {
    private String imageUrl;
    private int colorId;
    private double score;

    public ImageColor(String imageUrl, int colorId, double score) {
        this.imageUrl = imageUrl;
        this.colorId = colorId;
        this.score = score;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getColorId() {
        return colorId;
    }

    public double getScore() {
        return score;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return String.format(
                "%s {imageUrl: %s, colorId: %d, score: %f}",
                getClass().getSimpleName(), imageUrl, colorId, score);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (null == o || getClass() != o.getClass()) {
            return false;
        }
        ImageColor imageColor = (ImageColor) o;
        return colorId == imageColor.colorId &&
                Objects.equals(imageUrl, imageColor.imageUrl) &&
                Double.compare(score, imageColor.score) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageUrl, colorId, score);
    }
}
