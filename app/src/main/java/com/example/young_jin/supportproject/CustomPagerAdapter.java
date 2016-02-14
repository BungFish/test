package com.example.young_jin.supportproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.young_jin.supportproject.activities.MembershipGuideActivity;
import com.example.young_jin.supportproject.activities.MyhamActivity;

import java.util.ArrayList;

/**
 * Created by Young-Jin on 2016-02-02.
 */
public class CustomPagerAdapter extends PagerAdapter {

    int[] mResources = {
            R.drawable.card1,
            R.drawable.card2,
            R.drawable.card3
    };

    int[] mColors = {
            R.array.sm,
            R.array.basic,
            R.array.drive
    };

    String[] card_names;
    Context mContext;
    LayoutInflater mLayoutInflater;

    public CustomPagerAdapter(Context context) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        card_names = mContext.getResources().getStringArray(R.array.card_names);

    }

    @Override
    public int getCount() {
        return mResources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
        imageView.setImageBitmap(
                decodeSampledBitmapFromResource(itemView.getResources(), mResources[position], 100, 100));

//        TextView textView = (TextView) itemView.findViewById(R.id.textView);
//        textView.setText(1 + position + " of the " + getCount());

//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(mContext, position+1+"", Toast.LENGTH_SHORT).show();
//            }
//        });

        TextView card_name = (TextView) itemView.findViewById(R.id.card_name);
        card_name.setText(card_names[position]);

        int[] colors = mContext.getResources().getIntArray(mColors[position]);

        LinearLayout mainLayout = (LinearLayout) itemView.findViewById(R.id.main_layout);

        //화면 가로 픽셀
        DisplayMetrics dM = mContext.getResources().getDisplayMetrics();
        int widthOfScreen = dM.widthPixels;

        //마진 dp to px
        final float scale = mContext.getResources().getDisplayMetrics().density;
        int pixels = (int) ((50*2) * scale + 0.5f);

        //레이아웃 가로 길이 설정
        View view_instance = (View)mainLayout;
        ViewGroup.LayoutParams params=view_instance.getLayoutParams();
        params.width = widthOfScreen-pixels;
        view_instance.setLayoutParams(params);

        LinearLayout firstLayout = (LinearLayout) itemView.findViewById(R.id.first_layout);
        firstLayout.setBackgroundColor(colors[0]);

        LinearLayout secondLayout = (LinearLayout) itemView.findViewById(R.id.second_layout);
        secondLayout.setBackgroundColor(colors[1]);

        LinearLayout thirdLayout = (LinearLayout) itemView.findViewById(R.id.third_layout);
        thirdLayout.setBackgroundColor(colors[2]);

        thirdLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity = (Activity) mContext;
//                Intent intent = new Intent(activity, MyhamActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                activity.startActivity(intent);

                Intent intent = new Intent(activity, MyhamActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                activity.startActivity(intent);

                activity.overridePendingTransition(R.anim.slide_in_right, R.anim.hold);
            }
        });



        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
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