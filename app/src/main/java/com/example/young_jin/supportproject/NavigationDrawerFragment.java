package com.example.young_jin.supportproject;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment implements RecyclerAdapter.ClickListener {

    private RecyclerView recyclerView;
    public static final String PREF_FILE_NAME = "pref";
    public static final String KEY_USER_LEARNED_DRAWER = "user_learned_drawer";
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private RecyclerAdapter adapter;
    private boolean mUserLearnedDrawer;
    private boolean mFromSavedInstanceState;
    private View containerView;
    private TextView username;
    public static final String DRAWABLES_PATH = ":values/";

    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserLearnedDrawer = Boolean.valueOf(readFromPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, "false"));

        if (savedInstanceState != null) {
            mFromSavedInstanceState = true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        username = (TextView) layout.findViewById(R.id.username);
        username.setText(readFromPreferences(getActivity(), "username", ""));

        ((LinearLayout)layout.findViewById(R.id.containerDrawerImage)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);

                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.hold);
            }
        });

        recyclerView = (RecyclerView) layout.findViewById(R.id.drawerList);
        adapter = new RecyclerAdapter(getActivity(), getData());
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return layout;
    }

    public List<DefaultModel> getData() {
        List<DefaultModel> list = new ArrayList<>();
        int[] icons = {R.drawable.ic_drive_eta_white_24dp, R.drawable.ic_work_white_24dp, R.drawable.ic_drive_eta_white_24dp, R.drawable.ic_work_white_24dp};

        String[] titles = getResources().getStringArray(R.array.category);
        for (int i = 0; i < titles.length; i++) {
            DefaultModel current = new DefaultModel();

            current.setIconId(icons[i]);
            current.setTitle(titles[i]);
            list.add(current);
        }

        return list;
    }


    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {
        containerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                if (!mUserLearnedDrawer) {
                    super.onDrawerOpened(drawerView);
                    mUserLearnedDrawer = true;
                    saveToPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, mUserLearnedDrawer + "");

                }

                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);

//                if (slideOffset < 0.3) {
//                    toolbar.setAlpha(1 - slideOffset);
//                }

            }
        };

        if (!mUserLearnedDrawer && mFromSavedInstanceState) {
            mDrawerLayout.openDrawer(containerView);
        }

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }

    public static void saveToPreferences(Context context, String preferenceName, String preferenceValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(preferenceName, preferenceValue);
        editor.commit();
    }

    public static String readFromPreferences(Context context, String preferenceName, String preferenceValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(preferenceName, preferenceValue);
    }

    @Override
    public void itemClick(View view, int position) {

        switch (position){
            case 0:
                if(getActivity().getTitle().equals("메뉴1")){
                    Toast.makeText(getActivity(), "현재 페이지 입니다.", Toast.LENGTH_SHORT).show();
                } else {
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_context, MyFragment.newInstance()).commit();
                    mDrawerLayout.closeDrawer(containerView);
                }
                break;
            case 1:
                if(getActivity().getTitle().equals("메뉴2")){
                    Toast.makeText(getActivity(), "현재 페이지 입니다.", Toast.LENGTH_SHORT).show();
                } else {
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_context, MyFragment2.newInstance()).commit();
                    mDrawerLayout.closeDrawer(containerView);
                }
                break;
            default:
                Toast.makeText(getActivity(), "개발중인 메뉴입니다 조금만 기다려주세요!", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
