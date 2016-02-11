package com.example.young_jin.supportproject.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.young_jin.supportproject.R;
import com.example.young_jin.supportproject.models.Card;
import com.example.young_jin.supportproject.singleton.CardLab;

import java.util.ArrayList;

/**
 * Created by Young-Jin on 2016-02-11.
 */
public class MyHamRecyclerAdapter extends RecyclerView.Adapter<MyHamRecyclerAdapter.MyViewHolder> {

    private final LayoutInflater inflater;
    private ArrayList<Card> data;
    private Activity activity;
    private ClickListener clickListener;

    int[] mResources = {
            R.drawable.card1,
            R.drawable.card2,
            R.drawable.card3,
            R.drawable.card1,
            R.drawable.card2,
            R.drawable.card3
    };

    public MyHamRecyclerAdapter(Activity activity) {
        inflater = LayoutInflater.from(activity);
        this.data = CardLab.get(activity).getmCards();
        this.activity = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.myham_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {


        holder.myHamCard.setImageDrawable(activity.getResources().getDrawable(data.get(position).getImage()));
        holder.card_name.setText(data.get(position).getText());
        if(position == getItemCount()-1){

        }
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
