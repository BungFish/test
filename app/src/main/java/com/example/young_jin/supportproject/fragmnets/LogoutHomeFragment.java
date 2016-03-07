package com.example.young_jin.supportproject.fragmnets;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.young_jin.supportproject.R;
import com.example.young_jin.supportproject.activities.LoginActivity;
import com.example.young_jin.supportproject.adapter.LogoutCardPagerAdapter;

public class LogoutHomeFragment extends Fragment {

    private ViewPager mViewPager;
    private LogoutCardPagerAdapter mLogoutCardPagerAdapter;
    private int widthOfScreen;
    private Intent intent;
    private SetPagerWidthTask setPagerWidthTask;

    public static LogoutHomeFragment newInstance() {
        return new LogoutHomeFragment();
    }

    public LogoutHomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_logout_home, container, false);
        RelativeLayout addHamardButton = (RelativeLayout) layout.findViewById(R.id.add_Hamcard_button);
        addHamardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                getActivity().startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.hold);
            }
        });


        mViewPager = (ViewPager) layout.findViewById(R.id.pager2);
        mViewPager.setVisibility(View.INVISIBLE);

        setPagerWidthTask = new SetPagerWidthTask();
        setPagerWidthTask.execute(null, null, null);

//        mViewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
//            @Override
//            public void transformPage(View page, float position) {
//                final float normalizedposition = Math.abs(Math.abs(position) - 1);
//                page.setScaleX(normalizedposition / 2 + 0.3f);
//                page.setScaleY(normalizedposition / 2 + 0.3f);
//            }
//        });

//        Button details_button = (Button) layout.findViewById(R.id.details_button);
//        details_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getActivity(), "기능 미완성", Toast.LENGTH_SHORT).show();
//            }
//        });

        return layout;
    }

    class SetPagerWidthTask extends AsyncTask<Void, Void, Void>{

        private float offset;

        @Override
        protected Void doInBackground(Void... params) {
            if(mLogoutCardPagerAdapter == null) {
                mLogoutCardPagerAdapter = new LogoutCardPagerAdapter(getActivity());
            }

            //화면 가로 픽셀
            DisplayMetrics dM = getResources().getDisplayMetrics();
            widthOfScreen = dM.widthPixels;

            //화면 가로 px -> dp
            final float scale = getActivity().getResources().getDisplayMetrics().density;
            int widthOfView = (int)((widthOfScreen - 0.5f)/scale); //in DP

            //적절하게 마진 설정
            int spaceBetweenViews = -80; // in DP
            offset = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, widthOfView + spaceBetweenViews, dM);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            mViewPager.setAdapter(mLogoutCardPagerAdapter);

            mViewPager.setPageMargin((int) (-widthOfScreen+offset));

            mViewPager.setOffscreenPageLimit(5);
            mViewPager.setClipChildren(false);

            mViewPager.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(setPagerWidthTask.isCancelled()){
            setPagerWidthTask.cancel(true);
        }

    }
}
