package com.example.young_jin.supportproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


/**
 * Created by Young-Jin on 2016-02-02.
 */
public class MyFragment2 extends Fragment{

    private ViewPager mViewPager;
    private CustomPagerAdapter mCustomPagerAdapter;
    private ViewPager mViewPager2;
    private CustomPagerAdapter2
            mCustomPagerAdapter2;
    private int dotsCount;
    private ImageView[] dots;
    private LinearLayout pager_indicator;
    private int widthOfScreen;

    public static MyFragment2 newInstance() {
        MyFragment2 fragment = new MyFragment2();
        return fragment;
    }

    public MyFragment2() {

// Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.my_fragment2, container, false);

        mCustomPagerAdapter = new CustomPagerAdapter(getActivity());

        mViewPager = (ViewPager) layout.findViewById(R.id.pager2);
        mViewPager.setAdapter(mCustomPagerAdapter);

        //모든 스크린에 최적화
        DisplayMetrics dM = getResources().getDisplayMetrics();
        widthOfScreen = dM.widthPixels;
        int widthOfView = 300; //in DP
        int spaceBetweenViews = 20; // in DP
        float offset = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, widthOfView+spaceBetweenViews, dM);
        mViewPager.setPageMargin((int) (-widthOfScreen +offset));

//        mViewPager.setPageMargin((int) (-widthOfScreen / 5));
        mViewPager.setOffscreenPageLimit(5);
        mViewPager.setClipChildren(false);

//        mViewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
//            @Override
//            public void transformPage(View page, float position) {
//                final float normalizedposition = Math.abs(Math.abs(position) - 1);
//                page.setScaleX(normalizedposition / 2 + 0.3f);
//                page.setScaleY(normalizedposition / 2 + 0.3f);
//            }
//        });

        Button details_button = (Button) layout.findViewById(R.id.details_button);
        details_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "기능 미완성", Toast.LENGTH_SHORT).show();
            }
        });

        mCustomPagerAdapter2 = new CustomPagerAdapter2(getActivity());

        mViewPager2 = (ViewPager) layout.findViewById(R.id.pager3);
        mViewPager2.setAdapter(mCustomPagerAdapter2);

        mViewPager2.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < dotsCount; i++) {
                    dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));
                }

                dots[position].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        pager_indicator = (LinearLayout) layout.findViewById(R.id.viewPagerCountDots);
        setUiPageViewController();

// Inflate the layout for this fragment
        return layout;
    }

    private void setUiPageViewController() {

        dotsCount = mCustomPagerAdapter2.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(getActivity());
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            //점 간격
            params.setMargins(8*widthOfScreen/1440, 0, 8*widthOfScreen/1440, 0);

            pager_indicator.addView(dots[i], params);
        }

        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));
    }


}
