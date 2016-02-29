package com.example.young_jin.supportproject.adapter;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.young_jin.supportproject.R;
import com.example.young_jin.supportproject.activities.MyhamActivity;
import com.example.young_jin.supportproject.models.Card;
import com.example.young_jin.supportproject.singleton.CardLab;

import java.util.ArrayList;

/**
 * Created by Young-Jin on 2016-02-14.
 */
public class BigHamRecyclerAdapter extends RecyclerView.Adapter<BigHamRecyclerAdapter.MyViewHolder> {

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
            R.drawable.card3,
            R.drawable.card1
    };

    int[] mColors = {
            R.array.sm,
            R.array.basic,
            R.array.drive,
            R.array.sm,
            R.array.basic,
            R.array.drive,
            R.array.sm
    };

    String[] card_names;

    public BigHamRecyclerAdapter(Activity activity) {
        inflater = LayoutInflater.from(activity);
        this.data = CardLab.get(activity).getmCards();
        this.activity = activity;

        card_names = activity.getResources().getStringArray(R.array.card_names);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.pager_item3, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.imageView.setImageBitmap(
                decodeSampledBitmapFromResource(activity.getResources(), mResources[position], 100, 100));
        holder.card_name.setText(card_names[position]);

//        int[] colors = activity.getResources().getIntArray(mColors[position]);
//
//        holder.firstLayout.setBackgroundColor(colors[0]);
//        holder.secondLayout.setBackgroundColor(colors[1]);
//        holder.thirdLayout.setBackgroundColor(colors[2]);

        holder.thirdLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(activity, MyhamActivity.class);
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
        private final LinearLayout firstLayout;
        private final LinearLayout secondLayout;
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
            View view_instance = (View) mainLayout;
            ViewGroup.LayoutParams params=view_instance.getLayoutParams();
            params.height = (int) (heightOfScreen * 0.6);
            view_instance.setLayoutParams(params);

            firstLayout = (LinearLayout) itemView.findViewById(R.id.first_layout);
            secondLayout = (LinearLayout) itemView.findViewById(R.id.second_layout);
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

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

}
