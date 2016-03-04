package com.example.young_jin.supportproject.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.young_jin.supportproject.ImageCache;
import com.example.young_jin.supportproject.R;
import com.example.young_jin.supportproject.models.Card;
import com.example.young_jin.supportproject.singleton.CardLab;

import java.util.ArrayList;

/**
 * Created by Young-Jin on 2016-02-11.
 */
public class MyHamRecyclerAdapter extends RecyclerView.Adapter<MyHamRecyclerAdapter.MyViewHolder> {

    private final LayoutInflater inflater;
    private final Animation card_selected_animation;
    private final Animation card_unselected_animation;
    private final ImageCache imageCache;
    private ArrayList<Card> data;
    private Activity activity;
    private ClickListener clickListener;
    private int mSelectedCard;

    int[] mResources = {
            R.drawable.card_info_sm,
            R.drawable.card_info_basic,
            R.drawable.card_info_drive,
            R.drawable.card_info_lpg,
            R.drawable.card_info_lpg_plus,
    };

    public MyHamRecyclerAdapter(Activity activity) {
        inflater = LayoutInflater.from(activity);
        this.data = CardLab.get(activity).getmCards();
        this.activity = activity;

        card_selected_animation = AnimationUtils.loadAnimation(activity, R.anim.card_selected);
        card_unselected_animation = AnimationUtils.loadAnimation(activity, R.anim.card_unselected);

        imageCache = new ImageCache(activity);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.myham_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        imageCache.loadBitmap(data.get(position).getImage(), holder.myHamCard);
        holder.card_name.setText(data.get(position).getText());

        holder.myHamCard.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
//                        holder.myHamCard.startAnimation(card_selected_animation);
                        break;
                    case MotionEvent.ACTION_UP:
//                        holder.myHamCard.startAnimation(card_unselected_animation);
                        mSelectedCard = position;
                        break;
                    case MotionEvent.ACTION_CANCEL:
//                        holder.myHamCard.startAnimation(card_unselected_animation);
                        break;

                }
                return false;
            }
        });

        if(mSelectedCard == position) {
            holder.myHamCard.setAlpha(1f);
        } else {
            holder.myHamCard.setAlpha(0.5f);
        }

//        if(position == getItemCount()-1){
//
//        }

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                holder.itemView.setAlpha(1f);
//            }
//        });

    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private LinearLayout firstLayout;
        private ImageView myHamCard;
        private TextView card_name;

        public MyViewHolder(View itemView) {
            super(itemView);
            myHamCard = (ImageView)itemView.findViewById(R.id.imageView);
            card_name = (TextView)itemView.findViewById(R.id.card_name);
            myHamCard.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            if (clickListener != null) {
                clickListener.itemClick(v, getPosition());
            }
        }
    }

    public interface ClickListener {

        public void itemClick(View view, int position);
    }

    public Card getItem(int position) {
        return data.get(position);
    }

    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

}
