package com.example.young_jin.supportproject.models;

import java.util.Date;

/**
 * Created by Young-Jin on 2016-03-07.
 */
public class CardDetailChid {

    private Date mDate;
    private boolean mSolved;

    public CardDetailChid(Date date, boolean solved) {
        mDate = date;
        mSolved = solved;
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
}