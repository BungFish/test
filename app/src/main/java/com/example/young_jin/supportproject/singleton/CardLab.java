package com.example.young_jin.supportproject.singleton;

import android.content.Context;

import com.example.young_jin.supportproject.R;
import com.example.young_jin.supportproject.models.Card;

import java.util.ArrayList;
import java.util.Date;

public class CardLab {

    int[] mResources = {
            R.drawable.membership_card_sm,
            R.drawable.membership_card_basic,
            R.drawable.membership_card_drive,
            R.drawable.membership_card_sm,
            R.drawable.membership_card_basic,
            R.drawable.membership_card_drive
    };

    String[] mStrings = {
            "HAM SM Card",
            "HAM Basic Card",
            "HAM Drive Card",
            "HAM SM Card",
            "HAM Basic Card",
            "HAM Drive Card"
    };

    int[] mColors = {
            R.array.sm,
            R.array.basic,
            R.array.drive,
            R.array.sm,
            R.array.basic,
            R.array.drive
    };

    private ArrayList<Card> mCards;

    private static CardLab sCrimeLab;
    private Context mAppContext;

    public CardLab(Context mAppContext) {
        this.mAppContext = mAppContext;
        mCards = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Card c = new Card();
            c.setImage(mResources[i]);
            c.setText(mStrings[i]);
            c.setColors(mAppContext.getResources().getIntArray(mColors[i]));
            c.setDueDate(new Date(2016,04,i+1));
            mCards.add(c);
        }
        Card c = new Card();
        c.setImage(R.drawable.myham_card_add);
        c.setText("카드 추가");
        c.setColors(mAppContext.getResources().getIntArray(mColors[0]));
        mCards.add(c);
    }

    public static CardLab get(Context c){
        if(sCrimeLab == null){
            sCrimeLab = new CardLab(c.getApplicationContext());
        }
        return sCrimeLab;
    }

    public ArrayList<Card> getmCards(){
        return mCards;
    }
}
