package com.example.young_jin.supportproject.fragmnets;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.young_jin.supportproject.R;
import com.example.young_jin.supportproject.adapter.MyAdapter;

/**
 * Created by Young-Jin on 2016-03-03.
 */
public class MembershipGuideFragment2 extends Fragment {

    private MyAdapter adapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    public static MembershipGuideFragment2 newInstance() {
        MembershipGuideFragment2 fragment = new MembershipGuideFragment2();
        return fragment;
    }

    public MembershipGuideFragment2() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_membership_guide2, container, false);

        viewPager = (ViewPager) layout.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) layout.findViewById(R.id.tabs);

        adapter = new MyAdapter(getChildFragmentManager());
        adapter.addFragment(new MembershipGuideFragment(), "포인트 활용형");
        adapter.addFragment(new MembershipGuideFragment(), "제휴사 기프트형");
        adapter.addFragment(new MembershipGuideFragment(), "체크/신용카드형");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        return layout;

    }
}
