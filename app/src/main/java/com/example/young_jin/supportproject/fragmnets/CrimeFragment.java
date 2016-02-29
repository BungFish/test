package com.example.young_jin.supportproject.fragmnets;

import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.example.young_jin.supportproject.R;

import java.lang.reflect.Field;

/**
 * Created by Young-Jin on 2016-02-08.
 */
public class CrimeFragment extends Fragment {

    private ViewPager mPager;
    private MyPagerAdapter pagerAdapter;
    private static CrimeFragment fragment;
    private PagerSlidingTabStrip tabs;

    public static CrimeFragment newInstance() {
        if(fragment == null) {
            fragment = new CrimeFragment();
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getRetainInstance();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_crime, container, false);

        mPager = (ViewPager) v.findViewById(R.id.pager);
        pagerAdapter = new MyPagerAdapter(getChildFragmentManager());
        mPager.setAdapter(pagerAdapter);
        mPager.setOffscreenPageLimit(5);

        tabs = (PagerSlidingTabStrip) v.findViewById(R.id.tabs);
        tabs.setViewPager(mPager);

        return v;
    }

    class MyPagerAdapter extends FragmentPagerAdapter {

        String[] tabTitle;

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
            tabTitle = getResources().getStringArray(R.array.tabs);
        }

        @Override
        public Fragment getItem(int position) {

            if(position == 0){
                return CrimeAddFragment.newInstance();
            } else {
                return CrimeListFragment.newInstance();
            }

        }

        @Override
        public CharSequence getPageTitle(int position) {

            return tabTitle[position];
        }

        @Override
        public int getCount() {
            return tabTitle.length;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
