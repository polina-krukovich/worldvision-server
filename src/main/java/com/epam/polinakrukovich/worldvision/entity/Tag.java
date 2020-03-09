package com.epam.polinakrukovich.worldvision.entity;

import java.util.Objects;

/**
 * Entity-class for storing tag data.
 *
 * @author Polina Krukovich
 */
public class Tag {
    private String tag;

    public Tag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return String.format(
                "%s {tag: %s}",
                getClass().getSimpleName(), tag);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (null == o || getClass() != o.getClass()) {
            return false;
        }
        Tag tag = (Tag) o;
        return Objects.equals(tag, tag.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tag);
    }
}
