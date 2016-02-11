package com.example.young_jin.supportproject.models;

/**
 * Created by Young-Jin on 2016-02-11.
 */
public class Card {
    private int image;
    private String text;
    private int[] colors;

    public Card() {
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int[] getColors() {
        return colors;
    }

    public void setColors(int[] colors) {
        this.colors = colors;
    }
}
