package com.example.young_jin.supportproject.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.young_jin.supportproject.ImageCache;
import com.example.young_jin.supportproject.R;
import com.example.young_jin.supportproject.activities.MyhamActivity;

public class LogoutCardPagerAdapter extends PagerAdapter {

    private final ImageCache imageCache;
    int[] mResources = {
            R.drawable.main_card_no_sm_wh,
            R.drawable.main_card_no_basic_wh,
            R.drawable.main_card_no_drive_wh
    };

    int[] mColors = {
            R.array.sm,
            R.array.basic,
            R.array.drive
    };

    String[] card_names;
    Context mContext;
    LayoutInflater mLayoutInflater;

    public LogoutCardPagerAdapter(Context context) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        imageCache = new ImageCache(context);
        card_names = mContext.getResources().getStringArray(R.array.card_names);

    }

    @Override
    public int getCount() {
        return mResources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = mLayoutInflater.inflate(R.layout.logout_card_pager_item, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
//        imageView.setImageBitmap(
//                decodeSampledBitmapFromResource(itemView.getResources(), mResources[position], 446, 276));
        imageCache.loadBitmap(mResources[position], imageView);


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
        View view_instance = mainLayout;
        ViewGroup.LayoutParams params = view_instance.getLayoutParams();
        params.width = widthOfScreen - pixels;
        view_instance.setLayoutParams(params);

        LinearLayout firstLayout = (LinearLayout) itemView.findViewById(R.id.first_layout);
        firstLayout.setBackgroundColor(colors[0]);

        LinearLayout secondLayout = (LinearLayout) itemView.findViewById(R.id.second_layout);
        secondLayout.setBackgroundColor(colors[1]);

        LinearLayout thirdLayout = (LinearLayout) itemView.findViewById(R.id.third_layout);
        mainLayout.setBackgroundColor(colors[2]);

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
}