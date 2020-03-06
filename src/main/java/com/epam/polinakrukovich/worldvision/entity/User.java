package com.epam.polinakrukovich.worldvision.entity;

import java.util.Objects;

/**
 * User is an entity-class for storing basic information about user
 * used to connect user with other DB resources.
 *
 * Other data, like password, photo url, username, etc., is supposed
 * to be accessed by frontend applications directly from Firebase.
 */
public class User {
    private int id;
    private String email;

    public User(int id, String email) {
        this.id = id;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format("%s {id: %d, email: %s}",
                getClass().getSimpleName(), id, email);
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
        return id == user.id &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }
}
