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
    private String userUid;
    private String url;
    private int likesCount;
    private int downloadsCount;
    private DateTime creationTime;
    private List<String> likedUserUids;
    private List<String> downloadedUserUids;

    public Image(int id, String userUid, String url, int likesCount,
                 int downloadsCount, DateTime creationTime,
                 List<String> likedUserUids, List<String> downloadedUserUids) {
        this.id = id;
        this.userUid = userUid;
        this.url = url;
        this.likesCount = likesCount;
        this.downloadsCount = downloadsCount;
        this.creationTime = creationTime;
        this.likedUserUids = likedUserUids;
        this.downloadedUserUids = downloadedUserUids;
    }

    public int getId() {
        return id;
    }

    public String getUserUid() {
        return userUid;
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

    public List<String> getLikedUserUids() {
        return likedUserUids;
    }

    public List<String> getDownloadedUserUids() {
        return downloadedUserUids;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
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

    public void setLikedUserUids(List<String> likedUserUids) {
        this.likedUserUids = likedUserUids;
    }

    public void setDownloadedUserUids(List<String> downloadedUserUids) {
        this.downloadedUserUids = downloadedUserUids;
    }

    @Override
    public java.lang.String toString() {
        return java.lang.String.format(
                "%s {" +
                        "id: %d, " +
                        "userUid: %s, " +
                        "url: %s, " +
                        "likesCount: %d, " +
                        "downloadsCount: %d, " +
                        "creationTime: %s, " +
                        "likedUserUids: %s, " +
                        "downloadedUserUids: %s}",
                getClass().getSimpleName(), id, userUid, url,
                likesCount, downloadsCount, creationTime,
                likedUserUids, downloadedUserUids);
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
                Objects.equals(userUid, image.userUid) &&
                Objects.equals(url, image.url) &&
                Objects.equals(creationTime, image.creationTime) &&
                Objects.equals(likedUserUids, image.likedUserUids) &&
                Objects.equals(downloadedUserUids, image.downloadedUserUids);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userUid, url, likesCount, downloadsCount,
                creationTime, likedUserUids, downloadedUserUids);
    }
}
