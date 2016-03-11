package com.example.young_jin.supportproject.models;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Young-Jin on 2016-02-08.
 */
public class Crime implements ParentObject{
    private UUID mID;
    private String mTItle;
    private Date mDate;
    private boolean mSolved;

    private List<Object> mChildrenList;

    public Crime() {
        this.mID = UUID.randomUUID();
        mDate = new Date();
    }

    public String getmTItle() {
        return mTItle;
    }

    public void setmTItle(String mTItle) {
        this.mTItle = mTItle;
    }

    public UUID getmID() {
        return mID;
    }

    public boolean ismSolved() {
        return mSolved;
    }

    public void setmSolved(boolean mSolved) {
        this.mSolved = mSolved;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    @Override
    public List<Object> getChildObjectList() {
        return mChildrenList;
    }

    @Override
    public void setChildObjectList(List<Object> list) {
        mChildrenList = list;
    }
}
