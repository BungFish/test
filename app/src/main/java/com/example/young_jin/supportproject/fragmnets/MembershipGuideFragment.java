package com.example.young_jin.supportproject.fragmnets;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.young_jin.supportproject.adapter.HamCardPagerAdapter;
import com.example.young_jin.supportproject.Item2OffsetDecoration;
import com.example.young_jin.supportproject.R;
import com.example.young_jin.supportproject.adapter.BigHamRecyclerAdapter;

/**
 * Created by Young-Jin on 2016-02-14.
 */
public class MembershipGuideFragment extends Fragment {

    private ViewPager mViewPager;
    private int widthOfScreen;
    private HamCardPagerAdapter mHamCardPagerAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private BigHamRecyclerAdapter adapter;

    public static MembershipGuideFragment newInstance() {
        MembershipGuideFragment fragment = new MembershipGuideFragment();
        return fragment;
    }

    public MembershipGuideFragment() {

// Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_membership_guide, container, false);

        recyclerView = (RecyclerView) layout.findViewById(R.id.myham_list);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new BigHamRecyclerAdapter(getActivity());
        recyclerView.setAdapter(adapter);

        Item2OffsetDecoration itemDecoration = new Item2OffsetDecoration(getActivity(), R.dimen.item2_offset);
        recyclerView.addItemDecoration(itemDecoration);

        return layout;

    }
}
