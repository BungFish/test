package com.example.young_jin.supportproject.models;

import java.util.Date;

/**
 * Created by Young-Jin on 2016-02-11.
 */
public class Card {
    private int image;
    private String text;
    private int[] colors;
    private Date dueDate;

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

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}
