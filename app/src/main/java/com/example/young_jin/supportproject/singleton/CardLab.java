package com.example.young_jin.supportproject.singleton;

import android.content.Context;

import com.example.young_jin.supportproject.R;
import com.example.young_jin.supportproject.models.Card;

import java.util.ArrayList;

/**
 * Created by Young-Jin on 2016-02-11.
 */
public class CardLab {

    int[] mResources = {
            R.drawable.card1,
            R.drawable.card2,
            R.drawable.card3,
            R.drawable.card1,
            R.drawable.card2,
            R.drawable.card3
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
        mCards = new ArrayList<Card>();
        for (int i = 0; i < 6; i++) {
            Card c = new Card();
            c.setImage(mResources[i]);
            c.setText(mStrings[i]);
            c.setColors(mAppContext.getResources().getIntArray(mColors[i]));
            mCards.add(c);
        }
        Card c = new Card();
        c.setImage(R.drawable.ic_add_white_24dp);
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
