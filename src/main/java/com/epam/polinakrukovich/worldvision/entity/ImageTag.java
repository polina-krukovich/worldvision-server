package com.epam.polinakrukovich.worldvision.entity;

import java.util.Objects;

/**
 * Entity-class for storing relation between image and tag.
 *
 * @author Polina Krukovich
 */
public class ImageTag {
    private String imageUrl;
    private String tag;

    public ImageTag(String imageUrl, String tag) {
        this.imageUrl = imageUrl;
        this.tag = tag;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTag() {
        return tag;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return String.format(
                "%s {imageUrl: %s, tag: %s}",
                getClass().getSimpleName(), imageUrl, tag);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (null == o || getClass() != o.getClass()) {
            return false;
        }
        ImageTag imageTag = (ImageTag) o;
        return Objects.equals(imageUrl, imageTag.imageUrl) &&
                Objects.equals(tag, imageTag.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageUrl, tag);
    }
}
