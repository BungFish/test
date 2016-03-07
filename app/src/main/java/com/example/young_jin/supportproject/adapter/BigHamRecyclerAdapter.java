package com.example.young_jin.supportproject.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.young_jin.supportproject.ImageCache;
import com.example.young_jin.supportproject.R;
import com.example.young_jin.supportproject.activities.CardDetailActivity;
import com.example.young_jin.supportproject.models.Card;
import com.example.young_jin.supportproject.singleton.CardLab;

import java.util.ArrayList;

public class BigHamRecyclerAdapter extends RecyclerView.Adapter<BigHamRecyclerAdapter.MyViewHolder> {

    private final LayoutInflater inflater;
    private final ImageCache imageCache;
    private ArrayList<Card> data;
    private Activity activity;
    private ClickListener clickListener;

    int[] mResources = {
            R.drawable.card1,
            R.drawable.card2,
            R.drawable.card3,
            R.drawable.card1,
            R.drawable.card2,
            R.drawable.card3,
            R.drawable.card1
    };

    String[] card_names;

    public BigHamRecyclerAdapter(Activity activity) {
        inflater = LayoutInflater.from(activity);
        this.data = CardLab.get(activity).getmCards();
        this.activity = activity;

        imageCache = new ImageCache(activity);
        card_names = activity.getResources().getStringArray(R.array.card_names);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.pager_item3, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        imageCache.loadBitmap(mResources[position], holder.imageView);
        holder.card_name.setText(card_names[position]);

//        int[] colors = activity.getResources().getIntArray(mColors[position]);
//
//        holder.firstLayout.setBackgroundColor(colors[0]);
//        holder.secondLayout.setBackgroundColor(colors[1]);
//        holder.thirdLayout.setBackgroundColor(colors[2]);

        holder.thirdLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(activity, CardDetailActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                activity.startActivity(intent);

                activity.overridePendingTransition(R.anim.slide_in_right, R.anim.hold);
            }
        });


    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ImageView imageView;
        private final TextView card_name;
        private final LinearLayout mainLayout;
        private final LinearLayout thirdLayout;

        public MyViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            card_name = (TextView) itemView.findViewById(R.id.card_name);

            mainLayout = (LinearLayout) itemView.findViewById(R.id.main_layout);

            //화면 가로 픽셀
            DisplayMetrics dM = activity.getResources().getDisplayMetrics();
            int heightOfScreen = dM.heightPixels;

//            //마진 dp to px
//            final float scale = activity.getResources().getDisplayMetrics().density;
//            int pixels = (int) ((200) * scale + 0.5f);

            //레이아웃 가로 길이 설정
            View view_instance =  mainLayout;
            ViewGroup.LayoutParams params=view_instance.getLayoutParams();
            params.height = (int) (heightOfScreen * 0.6);
            view_instance.setLayoutParams(params);

            thirdLayout = (LinearLayout) itemView.findViewById(R.id.third_layout);
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
