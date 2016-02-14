package com.example.young_jin.supportproject.fragmnets;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.young_jin.supportproject.CircularOffsetDecoration;
import com.example.young_jin.supportproject.adapter.HamRecyclerAdapter;
import com.example.young_jin.supportproject.adapter.MyHamRecyclerAdapter;
import com.example.young_jin.supportproject.ItemOffsetDecoration;
import com.example.young_jin.supportproject.R;
import com.example.young_jin.supportproject.models.Card;

import java.util.Random;

/**
 * Created by Young-Jin on 2016-02-11.
 */
public class MyHamFragment extends Fragment implements MyHamRecyclerAdapter.ClickListener{

    private ViewPager mViewPager;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    int[] mColors = {
            R.array.sm,
            R.array.basic,
            R.array.drive
    };
    private HamRecyclerAdapter adapter;
    private int widthOfScreen;
    private RecyclerView mMyHamRecyclerView;
    private MyHamRecyclerAdapter mMyHamAdapter;
    private LinearLayoutManager mMyHamlinearLayoutManager;
    private ImageView ham_card;
    private TextView card_name;
    private LinearLayout firstLayout;
    private LinearLayout secondLayout;
    private CoordinatorLayout coordinatorLayout;
    private Snackbar snackbar;
    private LinearLayout mainLayout;
    private Random rd;
    private Animation fadeIn;
    private Animation anim;

    public static MyHamFragment newInstance() {
        MyHamFragment fragment = new MyHamFragment();
        return fragment;
    }

    public MyHamFragment() {

// Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View layout = inflater.inflate(R.layout.fragment_myham, container, false);


        coordinatorLayout = (CoordinatorLayout)layout.findViewById(R.id.coordinatorLayout);
        ham_card = (ImageView) layout.findViewById(R.id.ham_card);
        card_name = (TextView) layout.findViewById(R.id.card_name);

        mMyHamRecyclerView = (RecyclerView) layout.findViewById(R.id.myham_list);
        mMyHamlinearLayoutManager = new LinearLayoutManager(getActivity());
        mMyHamlinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mMyHamRecyclerView.setLayoutManager(mMyHamlinearLayoutManager);

        mMyHamAdapter = new MyHamRecyclerAdapter(getActivity());
        mMyHamAdapter.setClickListener(this);
        mMyHamRecyclerView.setAdapter(mMyHamAdapter);


        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(getActivity(), R.dimen.item_offset);
        mMyHamRecyclerView.addItemDecoration(itemDecoration);

        recyclerView = (RecyclerView) layout.findViewById(R.id.my_list);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new HamRecyclerAdapter(getActivity());
        recyclerView.setAdapter(adapter);

        CircularOffsetDecoration circularDecoration = new CircularOffsetDecoration(getActivity(), R.dimen.circular_offset);
        recyclerView.addItemDecoration(circularDecoration);

        int[] colors = getResources().getIntArray(mColors[1]);

        mainLayout = (LinearLayout) layout.findViewById(R.id.main_layout);

        firstLayout = (LinearLayout) layout.findViewById(R.id.first_layout);
        firstLayout.setBackgroundColor(colors[0]);

        secondLayout = (LinearLayout) layout.findViewById(R.id.second_layout);
        secondLayout.setBackgroundColor(colors[1]);

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

        rd = new Random();

//        fadeIn = new AlphaAnimation(0, 1);
//        fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
//        fadeIn.setDuration(1000);
//
//        Animation fadeOut = new AlphaAnimation(1, 0);
//        fadeOut.setInterpolator(new AccelerateInterpolator()); //and this
//        fadeOut.setStartOffset(1000);
//        fadeOut.setDuration(1000);
//
//        AnimationSet animation = new AnimationSet(false); //change to false
//        animation.addAnimation(fadeIn);
//        animation.addAnimation(fadeOut);

        anim = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);

// Inflate the layout for this fragment
        return layout;
    }

    @Override
    public void itemClick(View view, final int position) {
        Card card = mMyHamAdapter.getItem(position);
        if(position == mMyHamAdapter.getItemCount()-1) {
            Toast.makeText(getActivity(), "카드등록화면 전환", Toast.LENGTH_SHORT).show();
        } else {
            mainLayout.startAnimation(anim);

            firstLayout.setBackgroundColor(card.getColors()[0]);
            secondLayout.setBackgroundColor(card.getColors()[1]);
            ham_card.setImageDrawable(getResources().getDrawable(card.getImage()));
            card_name.setText(card.getText());

            firstLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "화면 전환" + position, Toast.LENGTH_SHORT).show();
                }
            });

            if(position==1||position==3) {
                snackbar = Snackbar.make(coordinatorLayout, card.getDueDate().getYear() + "년 " + card.getDueDate().getMonth() + "월 " + card.getDueDate().getDay() + "일" + "에 만료됩니다.", Snackbar.LENGTH_INDEFINITE)
                        .setAction("연장", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getActivity(), "연장 화면", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setActionTextColor(getResources().getColor(R.color.primary));

                snackbar.show();

            } else {
                if(snackbar != null) {
                    snackbar.dismiss();
                }
            }
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_myham, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_card:
                Toast.makeText(getActivity(), "카드등록화면 전환", Toast.LENGTH_SHORT).show();
                break;

        }
        return true;
    }

    public void randomAnimation(){
        switch (rd.nextInt(2)){
            case 0:
                mainLayout.setTranslationX(1500);
                mainLayout.animate().translationX(0).setDuration(500);
                break;
            case 1:
                mainLayout.setTranslationX(-1500);
                mainLayout.animate().translationX(-0).setDuration(500);
                break;

        }
    }
}
