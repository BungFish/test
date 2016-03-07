package com.example.young_jin.supportproject.fragmnets;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.young_jin.supportproject.R;
import com.example.young_jin.supportproject.adapter.BannerPagerAdapter;
import com.example.young_jin.supportproject.adapter.LoginCardPagerAdapter;

public class LoginHomeFragment extends Fragment {

    private int widthOfScreen;
    private BannerPagerAdapter mCustomPagerAdapter;
    private ViewPager mViewPager;
    private ImageView[] dots;
    private ImageView[] dots2;
    private LinearLayout pager_indicator;
    private ViewPager myCardViewPager;
    private LoginCardPagerAdapter loginCardPagerAdapter;
    private LinearLayout pager_indicator2;
    private LoadPagerAdapterTask loadPagerAdapterTask;

    public static LoginHomeFragment newInstance() {
        return new LoginHomeFragment();
    }

    public LoginHomeFragment() {
// Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_login_home, container, false);

        DisplayMetrics dM = getResources().getDisplayMetrics();
        widthOfScreen = dM.widthPixels;

        mViewPager = (ViewPager) layout.findViewById(R.id.pager);
        mViewPager.setVisibility(View.INVISIBLE);

        myCardViewPager = (ViewPager) layout.findViewById(R.id.pager2);
        myCardViewPager.setVisibility(View.INVISIBLE);

        pager_indicator = (LinearLayout) layout.findViewById(R.id.viewPagerCountDots);
        pager_indicator2 = (LinearLayout) layout.findViewById(R.id.viewPagerCountDots2);

        loadPagerAdapterTask = new LoadPagerAdapterTask();
        loadPagerAdapterTask.execute(null, null, null);

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

        return layout;
    }

    private ImageView[] setUiPageViewController(LinearLayout indicator, int dotsCount, int selected, int nonSelected) {

        ImageView imageViews[] = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {
            imageViews[i] = new ImageView(getActivity());
            imageViews[i].setImageDrawable(getResources().getDrawable(selected));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            //점 간격
            params.setMargins(8 * widthOfScreen / 1440, 0, 8 * widthOfScreen / 1440, 0);

            indicator.addView(imageViews[i], params);
        }

        imageViews[0].setImageDrawable(getResources().getDrawable(nonSelected));

        return imageViews;
    }

    class LoadPagerAdapterTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            mCustomPagerAdapter = new BannerPagerAdapter(getActivity());
            loginCardPagerAdapter = new LoginCardPagerAdapter(getActivity());

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            mViewPager.setAdapter(mCustomPagerAdapter);
            myCardViewPager.setAdapter(loginCardPagerAdapter);

            dots = setUiPageViewController(pager_indicator, mCustomPagerAdapter.getCount(), R.drawable.nonselecteditem_dot, R.drawable.selecteditem_dot);
            dots2 = setUiPageViewController(pager_indicator2, loginCardPagerAdapter.getCount(), R.drawable.nonselecteditem_dot2, R.drawable.selecteditem_dot);

            mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    for (int i = 0; i < mCustomPagerAdapter.getCount(); i++) {
                        dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));
                    }

                    dots[position].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

            myCardViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    for (int i = 0; i < loginCardPagerAdapter.getCount(); i++) {
                        dots2[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot2));
                    }

                    dots2[position].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

            mViewPager.setVisibility(View.VISIBLE);
            myCardViewPager.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (!loadPagerAdapterTask.isCancelled()) {
            loadPagerAdapterTask.cancel(true);
        }
    }

}
