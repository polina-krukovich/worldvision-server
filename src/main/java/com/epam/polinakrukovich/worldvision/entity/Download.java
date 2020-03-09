package com.epam.polinakrukovich.worldvision.entity;

import org.joda.time.DateTime;

import java.util.Objects;

/**
 * Entity-class for storing download data.
 *
 * @author Polina Krukovich
 */
public class Download {
    private int id;
    private String imageUrl;
    private String userId;
    private DateTime creationTime;

    public Download(int id, String imageUrl, String userId, DateTime creationTime) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.userId = userId;
        this.creationTime = creationTime;
    }

    public int getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getUserId() {
        return userId;
    }

    public DateTime getCreationTime() {
        return creationTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setCreationTime(DateTime creationTime) {
        this.creationTime = creationTime;
    }

    @Override
    public String toString() {
        return String.format(
                "%s {id: %d, imageUrl: %s, userId: %s, creationTime: %s}",
                getClass().getSimpleName(), id, imageUrl, userId, creationTime);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (null == o || getClass() != o.getClass()) {
            return false;
        }
        Download download = (Download) o;
        return id == download.id &&
                Objects.equals(imageUrl, download.imageUrl) &&
                Objects.equals(userId, download.userId) &&
                Objects.equals(creationTime, download.creationTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, imageUrl, userId, creationTime);
    }
}
