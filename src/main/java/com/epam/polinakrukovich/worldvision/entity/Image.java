package com.epam.polinakrukovich.worldvision.entity;

import com.google.api.client.util.DateTime;

import java.util.Objects;

/**
 * Image is an entity-class for storing full image data.
 *
 * @author Polina Krukovich
 */
public class Image {
    private int id;
    private int userId;
    private String url;
    private int likesCount;
    private int downloadsCount;
    private DateTime creationTime;

    public Image(int id, int userId, String url, int likesCount,
                 int downloadsCount, DateTime creationTime) {
        this.id = id;
        this.userId = userId;
        this.url = url;
        this.likesCount = likesCount;
        this.downloadsCount = downloadsCount;
        this.creationTime = creationTime;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getUrl() {
        return url;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public int getDownloadsCount() {
        return downloadsCount;
    }

    public DateTime getCreationTime() {
        return creationTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public void setDownloadsCount(int downloadsCount) {
        this.downloadsCount = downloadsCount;
    }

    public void setCreationTime(DateTime creationTime) {
        this.creationTime = creationTime;
    }

    @Override
    public java.lang.String toString() {
        return java.lang.String.format(
                "%s {" +
                        "id: %d, " +
                        "userId: %d, " +
                        "url: %s, " +
                        "likesCount: %d, " +
                        "downloadsCount: %d, " +
                        "creationTime: %s}",
                getClass().getSimpleName(), id, userId, url,
                likesCount, downloadsCount, creationTime);
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
        return id == image.id &&
                likesCount == image.likesCount &&
                downloadsCount == image.downloadsCount &&
                Objects.equals(userId, image.userId) &&
                Objects.equals(url, image.url) &&
                Objects.equals(creationTime, image.creationTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, url, likesCount,
                downloadsCount, creationTime);
    }
}
