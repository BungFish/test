package com.example.young_jin.supportproject;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.DimenRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Young-Jin on 2016-02-14.
 */
public class Item2OffsetDecoration extends RecyclerView.ItemDecoration {

    private int mItemOffset;

    public Item2OffsetDecoration(int itemOffset) {
        mItemOffset = itemOffset;
    }

    public Item2OffsetDecoration(@NonNull Context context, @DimenRes int itemOffsetId) {
        this(context.getResources().getDimensionPixelSize(itemOffsetId));
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(0, mItemOffset, 0, mItemOffset);
    }
}
