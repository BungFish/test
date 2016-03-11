package com.example.young_jin.supportproject.models;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;

import java.util.List;

/**
 * Created by Young-Jin on 2016-03-07.
 */
public class CardDetail implements ParentObject {

    /* Create an instance variable for your list of children */
    private List<Object> mChildrenList;
    private String title;
    private int imageSrc;

    public CardDetail(String title, int imageSrc) {
        this.imageSrc = imageSrc;
        this.title = title;
    }

    @Override
    public List<Object> getChildObjectList() {
        return mChildrenList;
    }

    @Override
    public void setChildObjectList(List<Object> list) {
        mChildrenList = list;
    }

    public int getImageSrc() {
        return imageSrc;
    }

    public String getTitle() {
        return title;
    }
}