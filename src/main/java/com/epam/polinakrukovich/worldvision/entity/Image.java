package com.epam.polinakrukovich.worldvision.entity;


import org.joda.time.DateTime;

import java.util.Objects;

/**
 * Entity-class for storing image data.
 *
 * @author Polina Krukovich
 */
public class Image {
    private String url;
    private String userId;
    private int downloadsCount;
    private DateTime creationTime;

    public Image(String userId, String url, int downloadsCount, DateTime creationTime) {
        this.userId = userId;
        this.url = url;
        this.downloadsCount = downloadsCount;
        this.creationTime = creationTime;
    }

    public String getUserId() {
        return userId;
    }

    public String getUrl() {
        return url;
    }

    public int getDownloadsCount() {
        return downloadsCount;
    }

    public DateTime getCreationTime() {
        return creationTime;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDownloadsCount(int downloadsCount) {
        this.downloadsCount = downloadsCount;
    }

    public void setCreationTime(DateTime creationTime) {
        this.creationTime = creationTime;
    }

    @Override
    public String toString() {
        return String.format(
                "%s {userId: %s, url: %s, downloadsCount: %d, creationTime: %s}",
                getClass().getSimpleName(), userId, url, downloadsCount, creationTime);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (null == o || getClass() != o.getClass()) {
            return false;
        }
        Image image = (Image) o;
        return downloadsCount == image.downloadsCount &&
                Objects.equals(userId, image.userId) &&
                Objects.equals(url, image.url) &&
                Objects.equals(creationTime, image.creationTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, url, downloadsCount, creationTime);
    }
}
