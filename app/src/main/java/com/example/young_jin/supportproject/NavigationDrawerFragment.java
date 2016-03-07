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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.young_jin.supportproject.activities.LoginActivity;
import com.example.young_jin.supportproject.activities.MembershipGuideActivity;
import com.example.young_jin.supportproject.activities.MyhamActivity;
import com.example.young_jin.supportproject.activities.SomethingMoreActivity;
import com.example.young_jin.supportproject.adapter.DrawerMenuAdapter;
import com.example.young_jin.supportproject.barcode.LoadBarcodeTask;
import com.example.young_jin.supportproject.fragmnets.LoginHomeFragment;
import com.example.young_jin.supportproject.fragmnets.LogoutHomeFragment;
import com.example.young_jin.supportproject.models.DrawerMenuItemModel;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment implements DrawerMenuAdapter.ClickListener {

    public static final String PREF_FILE_NAME = "pref";
    public static final String KEY_USER_LEARNED_DRAWER = "user_learned_drawer";
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private boolean mUserLearnedDrawer;
    private boolean mFromSavedInstanceState;
    private View containerView;
    private TextView username;
    public static final String DRAWABLES_PATH = ":values/";
    private TextView myham;
    private Intent intent;
    private Boolean mIsLoggedIn = false;
    private TextView nim;
    private Toolbar toolbar;
    private ImageView powerButton;

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
        saveToPreferences(getActivity(), "username", "홍길동");
        username = (TextView) layout.findViewById(R.id.username);
//        username.setText(readFromPreferences(getActivity(), "username", ""));

        myham = (TextView) layout.findViewById(R.id.myham);
        myham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mIsLoggedIn) {
                    Intent intent = new Intent(getActivity(), MyhamActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);

                    getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.hold);
                } else {
                    Toast.makeText(getActivity(), "회원가입 페이지", Toast.LENGTH_SHORT).show();
                    intent = new Intent(getActivity(), LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    getActivity().startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.hold);
                }
            }
        });

        nim = (TextView) layout.findViewById(R.id.nim);

        powerButton = (ImageView) layout.findViewById(R.id.power_button);
        LinearLayout logoff = (LinearLayout) layout.findViewById(R.id.logoff);
        logoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsLoggedIn) {
                    mIsLoggedIn = false;
                    username.setText("로그인해주세요.");
                    myham.setText("회원가입");
                    powerButton.setColorFilter(R.color.black_opacity_soft);
                    nim.setVisibility(View.GONE);
                    Toast.makeText(getActivity(), "로그아웃", Toast.LENGTH_SHORT).show();
                } else {
                    mIsLoggedIn = true;
                    username.setText("홍길동");
                    myham.setText(getResources().getString(R.string.title_activity_myham));
                    powerButton.setColorFilter(getResources().getColor(R.color.red), android.graphics.PorterDuff.Mode.MULTIPLY);
                    nim.setVisibility(View.VISIBLE);
                    Toast.makeText(getActivity(), "로그인", Toast.LENGTH_SHORT).show();
                    new LoadBarcodeTask().execute("87344535311", null, null);
                    new LoadBarcodeTask().execute("78762432423", null, null);
                    new LoadBarcodeTask().execute("23402395343", null, null);
                }
            }
        });

//        ((LinearLayout)layout.findViewById(R.id.containerDrawerImage)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), MainActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                startActivity(intent);
//
//                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.hold);
//            }
//        });

        RecyclerView recyclerView = (RecyclerView) layout.findViewById(R.id.drawerList);
        DrawerMenuAdapter adapter = new DrawerMenuAdapter(getActivity(), getData());
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return layout;
    }

    public List<DrawerMenuItemModel> getData() {
        List<DrawerMenuItemModel> list = new ArrayList<>();
        int[] icons = {R.drawable.ic_home_white_24dp, R.drawable.ic_credit_card_white_24dp, R.drawable.ic_place_white_24dp, R.drawable.ic_more_vert_white_24dp};

        String[] titles = getResources().getStringArray(R.array.category);
        for (int i = 0; i < titles.length; i++) {
            DrawerMenuItemModel current = new DrawerMenuItemModel();

            current.setIconId(icons[i]);
            current.setTitle(titles[i]);
            list.add(current);
        }

        return list;
    }


    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar, final RelativeLayout relativeLayout) {
        containerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        this.toolbar = toolbar;
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
                relativeLayout.setScaleX(0.95f + (1-slideOffset)*0.05f);
                relativeLayout.setScaleY(0.95f + (1-slideOffset)*0.05f);

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

        if(mIsLoggedIn){
            getFragmentManager()
                    .beginTransaction().setCustomAnimations(R.anim.fragment_fade_in, R.anim.fragment_fade_out)
                    .replace(R.id.main_context, LoginHomeFragment.newInstance()).commit();
        } else {
            getFragmentManager()
                    .beginTransaction().setCustomAnimations(R.anim.fragment_fade_in, R.anim.fragment_fade_out)
                    .replace(R.id.main_context, LogoutHomeFragment.newInstance()).commit();
        }
    }

    public static void saveToPreferences(Context context, String preferenceName, String preferenceValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(preferenceName, preferenceValue);
        editor.apply();
    }

    public static String readFromPreferences(Context context, String preferenceName, String preferenceValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(preferenceName, preferenceValue);
    }

    @Override
    public void itemClick(View view, int position) {
        int menu_state = -1;
        if(menu_state == position) {
            Toast.makeText(getActivity(), "같은 메뉴입니다.", Toast.LENGTH_SHORT).show();
        } else {
            switch (position) {
                case 0:
//                    menu_state = 0;

                    if(mIsLoggedIn){
                        toolbar.setBackgroundResource(R.drawable.toolbar_gradient);
                        getFragmentManager()
                                .beginTransaction().setCustomAnimations(R.anim.fragment_fade_in, R.anim.fragment_fade_out)
                                .replace(R.id.main_context, LoginHomeFragment.newInstance()).commit();
                    } else {
                        toolbar.setBackgroundColor(getResources().getColor(R.color.background_floating_material_dark));
                        getFragmentManager()
                                .beginTransaction().setCustomAnimations(R.anim.fragment_fade_in, R.anim.fragment_fade_out)
                                .replace(R.id.main_context, LogoutHomeFragment.newInstance()).commit();
                    }

                    mDrawerLayout.closeDrawer(containerView);
                    break;
                case 1:
//                    menu_state = 1;

                    intent = new Intent(getActivity(), MembershipGuideActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    getActivity().startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.hold);

                    break;
//                case 2:
//                    menu_state = 2;
//
//                    mDrawerLayout.closeDrawer(containerView);
//                    break;
                case 3:
                    intent = new Intent(getActivity(), SomethingMoreActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    getActivity().startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.hold);

//                    getFragmentManager()
//                            .beginTransaction().setCustomAnimations(R.anim.fragment_fade_in, R.anim.fragment_fade_out)
//                            .replace(R.id.main_context, CrimeFragment.newInstance()).commit();
//                    mDrawerLayout.closeDrawer(containerView);
                    break;
                default:
                    Toast.makeText(getActivity(), "준비중입니다.", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
