package com.epam.polinakrukovich.worldvision.entity;

import com.google.api.client.util.DateTime;

import java.util.List;
import java.util.Objects;

/**
 * Image is an entity-class for storing full image data.
 *
 * @author Polina Krukovich
 */
public class Image {
    private int id;
    private User user;
    private String url;
    private int likesCount;
    private int downloadsCount;
    private DateTime creationTime;
    private List<User> likedUsers;
    private List<User> downloadedUsers;

    public Image(int id, User user, String url, int likesCount,
                 int downloadsCount, DateTime creationTime,
                 List<User> likedUsers, List<User> downloadedUsers) {
        this.id = id;
        this.user = user;
        this.url = url;
        this.likesCount = likesCount;
        this.downloadsCount = downloadsCount;
        this.creationTime = creationTime;
        this.likedUsers = likedUsers;
        this.downloadedUsers = downloadedUsers;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
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

    public List<User> getLikedUsers() {
        return likedUsers;
    }

    public List<User> getDownloadedUsers() {
        return downloadedUsers;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
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

    public void setLikedUsers(List<User> likedUsers) {
        this.likedUsers = likedUsers;
    }

    public void setDownloadedUsers(List<User> downloadedUsers) {
        this.downloadedUsers = downloadedUsers;
    }

    @Override
    public String toString() {
        return String.format(
                "%s {" +
                        "id: %d, " +
                        "user: %s, " +
                        "url: %s, " +
                        "likesCount: %d, " +
                        "downloadsCount: %d, " +
                        "creationTime: %s, " +
                        "likedUsers: %s, " +
                        "downloadedUsers: %s}",
                getClass().getSimpleName(), id, user, url,
                likesCount, downloadsCount, creationTime,
                likedUsers, downloadedUsers);
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
                Objects.equals(user, image.user) &&
                Objects.equals(url, image.url) &&
                Objects.equals(creationTime, image.creationTime) &&
                Objects.equals(likedUsers, image.likedUsers) &&
                Objects.equals(downloadedUsers, image.downloadedUsers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, url, likesCount, downloadsCount,
                creationTime, likedUsers, downloadedUsers);
    }
}
