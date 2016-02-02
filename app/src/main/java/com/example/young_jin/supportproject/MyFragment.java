package com.example.young_jin.supportproject;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Gallery;
import android.widget.TextView;

/**
 * Created by Young-Jin on 2016-02-02.
 */
public class MyFragment extends Fragment{

    private CustomPagerAdapter mCustomPagerAdapter;
    private ViewPager mViewPager;
    private Gallery picGallery;

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

// Inflate the layout for this fragment
        return layout;
    }
}
