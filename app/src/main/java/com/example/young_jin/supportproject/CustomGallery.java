package com.example.young_jin.supportproject;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Gallery;

/**
 * Created by Young-Jin on 2016-02-02.
 */
public class CustomGallery extends Gallery {

    public CustomGallery(Context context) {
        super(context);
    }

    public CustomGallery(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomGallery(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY){
        return true;
    }
}