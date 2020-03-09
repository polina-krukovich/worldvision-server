package com.epam.polinakrukovich.worldvision.entity;

import org.joda.time.DateTime;

import java.util.Objects;

/**
 * Entity-class for storing like data.
 *
 * @author Polina Krukovich
 */
public class Like {
    private String imageUrl;
    private String userId;
    private DateTime creationTime;

    public Like(String imageUrl, String userId, DateTime creationTime) {
        this.imageUrl = imageUrl;
        this.userId = userId;
        this.creationTime = creationTime;
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
                "%s {imageUrl: %s, userId: %s, creationTime: %s}",
                getClass().getSimpleName(), imageUrl, userId, creationTime);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (null == o || getClass() != o.getClass()) {
            return false;
        }
        Like like = (Like) o;
        return Objects.equals(imageUrl, like.imageUrl) &&
                Objects.equals(userId, like.userId) &&
                Objects.equals(creationTime, like.creationTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageUrl, userId, creationTime);
    }
}
