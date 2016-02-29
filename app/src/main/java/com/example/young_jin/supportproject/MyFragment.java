package com.example.young_jin.supportproject;


import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.young_jin.supportproject.adapter.HamRecyclerAdapter;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

/**
 * Created by Young-Jin on 2016-02-02.
 */
public class MyFragment extends Fragment {

    private int widthOfScreen;
    private CustomPagerAdapter2 mCustomPagerAdapter;
    private ViewPager mViewPager;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    int[] mColors = {
            R.array.sm,
            R.array.basic,
            R.array.drive
    };
    private HamRecyclerAdapter adapter;
    private int dotsCount;
    private ImageView[] dots;
    private LinearLayout pager_indicator;
    private SlidingUpPanelLayout slidingUpPanelLayout;

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

        slidingUpPanelLayout = (SlidingUpPanelLayout) layout.findViewById(R.id.sliding_layout);

        DisplayMetrics dM = getResources().getDisplayMetrics();
        widthOfScreen = dM.widthPixels;

        mCustomPagerAdapter = new CustomPagerAdapter2(getActivity());

        mViewPager = (ViewPager) layout.findViewById(R.id.pager);
        mViewPager.setAdapter(mCustomPagerAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        recyclerView = (RecyclerView) layout.findViewById(R.id.my_list);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new HamRecyclerAdapter(getActivity());
            recyclerView.setAdapter(adapter);

            CircularOffsetDecoration circularDecoration = new CircularOffsetDecoration(getActivity(), R.dimen.circular_offset);
            recyclerView.addItemDecoration(circularDecoration);

        int[] colors = getResources().getIntArray(mColors[1]);

        LinearLayout firstLayout = (LinearLayout) layout.findViewById(R.id.first_layout);
        firstLayout.setBackgroundColor(colors[0]);

//        firstLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
//            }
//        });

        LinearLayout secondLayout = (LinearLayout) layout.findViewById(R.id.second_layout);
        secondLayout.setBackgroundColor(colors[1]);

//        LinearLayout thirdLayout = (LinearLayout) layout.findViewById(R.id.third_layout);
//        thirdLayout.setBackgroundColor(getResources().getColor(R.color.hint));
//
//        thirdLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getActivity(), "기능 미완성", Toast.LENGTH_SHORT).show();
//            }
//        });

//        textView2 = (TextView) layout.findViewById(R.id.textView2);
////        textView2.setText(state+"");
//        textView2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FragmentManager fm = getActivity().getSupportFragmentManager();
////                DatePickerFragment dialog = new DatePickerFragment();
//                DatePickerFragment dialog = DatePickerFragment.newInstance(new Date());
//                dialog.show(fm,"date");
//            }
//        });

        ImageView show_card = (ImageView) layout.findViewById(R.id.show_card);

        show_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.setContentView(R.layout.show_card);
                dialog.setCanceledOnTouchOutside(true);
                dialog.show();

            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
//
        pager_indicator = (LinearLayout) layout.findViewById(R.id.viewPagerCountDots);
        setUiPageViewController();

// Inflate the layout for this fragment
        return layout;
    }

    private void setUiPageViewController() {

        dotsCount = mCustomPagerAdapter.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(getActivity());
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            //점 간격
            params.setMargins(8* widthOfScreen /1440, 0, 8* widthOfScreen /1440, 0);

            pager_indicator.addView(dots[i], params);
        }

        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));
    }
}
