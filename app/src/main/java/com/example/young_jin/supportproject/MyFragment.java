package com.example.young_jin.supportproject;


import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;

/**
 * Created by Young-Jin on 2016-02-02.
 */
public class MyFragment extends Fragment{

    private CustomPagerAdapter mCustomPagerAdapter;
    private ViewPager mViewPager;
    private TextView textView2;
    private int state = 1;

    public static MyFragment newInstance() {
        MyFragment fragment = new MyFragment();
        return fragment;
    }

    public MyFragment() {

// Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.my_fragment, container, false);

        mCustomPagerAdapter = new CustomPagerAdapter(getActivity());

        mViewPager = (ViewPager) layout.findViewById(R.id.pager);
        mViewPager.setAdapter(mCustomPagerAdapter);

        DisplayMetrics dm = getActivity().getApplicationContext().getResources().getDisplayMetrics();
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        mViewPager.setPageMargin((int) (-width/2.2));
        mViewPager.setOffscreenPageLimit(5);
        mViewPager.setClipChildren(false);
        mViewPager.setCurrentItem(1);

        mViewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                final float normalizedposition = Math.abs(Math.abs(position) - 1);
                page.setScaleX(normalizedposition / 2 + 0.3f);
                page.setScaleY(normalizedposition / 2 + 0.3f);

                page.setRotationY(position * -30);
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                getActivity().setTitle(position+"번째 이미지");
                textView2.setText((state++)+"");

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        textView2 = (TextView) layout.findViewById(R.id.textView2);
//        textView2.setText(state+"");
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
//                DatePickerFragment dialog = new DatePickerFragment();
                DatePickerFragment dialog = DatePickerFragment.newInstance(new Date());
                dialog.show(fm,"date");
            }
        });

        ImageView show_card = (ImageView) layout.findViewById(R.id.show_card);
        show_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.show_card);

                dialog.show();
            }
        });


// Inflate the layout for this fragment
        return layout;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.menu_main, menu);
    }
}
