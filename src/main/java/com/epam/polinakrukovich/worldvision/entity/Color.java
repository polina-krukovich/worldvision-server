package com.epam.polinakrukovich.worldvision.entity;

import java.util.Objects;

/**
 * Entity-class for storing color data.
 *
 * @author Polina Krukovich
 */
public class Color {
    private int id;
    private int red;
    private int green;
    private int blue;

    public Color(int id, int red, int green, int blue) {
        this.id = id;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int getId() {
        return id;
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    @Override
    public String toString() {
        return String.format(
                "%s {id: %d, red: %d, green: %d, blue: %s}",
                getClass().getSimpleName(), id, red, green, blue);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (null == o || getClass() != o.getClass()) {
            return false;
        }
        Color color = (Color) o;
        return id == color.id &&
                red == color.red &&
                green == color.green &&
                blue == color.blue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, red, green, blue);
    }
}
