package com.epam.polinakrukovich.worldvision.entity;

import java.util.Arrays;
import java.util.Objects;

public class User {
    private String uid;
    private String email;
    private String displayName;
    private String phone;
    private String photoUrl;
    private String[] providers;
    private String creationTime;
    private String lastSignInTime;
    private boolean disabled;

    public User() {}

    public User(String uid, String email, String displayName, String phone, String photoUrl,
                String[] providers, String creationTime, String lastSignInTime, boolean disabled) {
        this.uid = uid;
        this.email = email;
        this.displayName = displayName;
        this.phone = phone;
        this.photoUrl = photoUrl;
        this.providers = providers;
        this.creationTime = creationTime;
        this.lastSignInTime = lastSignInTime;
        this.disabled = disabled;
    }

    public String getUid() {
        return uid;
    }

    public String getEmail() {
        return email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getPhone() {
        return phone;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String[] getProviders() {
        return providers;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public String getLastSignInTime() {
        return lastSignInTime;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public void setProviders(String[] providers) {
        this.providers = providers;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public void setLastSignInTime(String lastSignInTime) {
        this.lastSignInTime = lastSignInTime;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    @Override
    public String toString() {
        return String.format(
                "%s {uid: %s, email: %s, displayName: %s, phone: %s, photoUrl: %s, " +
                        "providers: %s, creationTime: %s, lastSignInTime: %s, disabled: %b}",
                getClass().getSimpleName(), uid, email, displayName, phone, photoUrl,
                Arrays.toString(providers), creationTime, lastSignInTime, disabled);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (null == o || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return disabled == user.disabled &&
                Objects.equals(uid, user.uid) &&
                Objects.equals(email, user.email) &&
                Objects.equals(displayName, user.displayName) &&
                Objects.equals(phone, user.phone) &&
                Objects.equals(photoUrl, user.photoUrl) &&
                Arrays.equals(providers, user.providers) &&
                Objects.equals(creationTime, user.creationTime) &&
                Objects.equals(lastSignInTime, user.lastSignInTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, email, displayName, phone, photoUrl,
                providers, creationTime, lastSignInTime, disabled);
    }
}
