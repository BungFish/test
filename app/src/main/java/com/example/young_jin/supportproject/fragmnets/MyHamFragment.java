package com.example.young_jin.supportproject.fragmnets;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.young_jin.supportproject.activities.MembershipGuideActivity;
import com.example.young_jin.supportproject.adapter.recycler_item_decoration.CircularOffsetDecoration;
import com.example.young_jin.supportproject.ImageCache;
import com.example.young_jin.supportproject.R;
import com.example.young_jin.supportproject.adapter.HamRecyclerAdapter;
import com.example.young_jin.supportproject.adapter.trash.MyHamRecyclerAdapter;
import com.example.young_jin.supportproject.models.Card;
import com.example.young_jin.supportproject.singleton.BarcodeManager;
import com.example.young_jin.supportproject.singleton.CardLab;

import java.util.ArrayList;

public class MyHamFragment extends Fragment implements MyHamRecyclerAdapter.ClickListener{

    int[] mResources = {
            R.drawable.membership_card_sm,
            R.drawable.membership_card_basic
    };

    String[] mStrings = {
            "HAM SM Card",
            "HAM Basic Card",
            "HAM Drive Card"
    };

    int[] mColors = {
            R.array.sm,
            R.array.basic,
            R.array.drive
    };

    String[] barcode = {
            "87344535311",
            "78762432423",
            "23402395343"
    };

    private TextView card_name;
    private LinearLayout firstLayout;
    private LinearLayout secondLayout;
    private CoordinatorLayout coordinatorLayout;
    private Snackbar snackbar;
    private LinearLayout mainLayout;
    private Animation anim;
    private ImageView[] hamcardImage;
    private TextView[] hamcardName;
    private Animation card_selected_animation;
    private ImageCache imageCache;
    private int mSelectedCard;
    private int mPreviouslySelectedCard;
    private ArrayList<Card> hamCards;
    private Intent intent;

    public static MyHamFragment newInstance() {
        return new MyHamFragment();
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

        setHasOptionsMenu(true);

        hamCards = CardLab.get(getActivity()).getmCards();
        card_selected_animation = AnimationUtils.loadAnimation(getActivity(), R.anim.card_selected);
        anim = AnimationUtils.loadAnimation(getActivity(), R.anim.scale_up_2);

        imageCache = new ImageCache(getActivity());

        hamcardImage = new ImageView[hamCards.size()];
        hamcardName = new TextView[hamCards.size()];

        hamcardImage[0] = (ImageView)layout.findViewById(R.id.hamcard1);
        hamcardImage[1] = (ImageView)layout.findViewById(R.id.hamcard2);
        hamcardImage[2] = (ImageView)layout.findViewById(R.id.hamcard3);

        hamcardName[0] = (TextView)layout.findViewById(R.id.hamcard_name1);
        hamcardName[1] = (TextView)layout.findViewById(R.id.hamcard_name2);
        hamcardName[2] = (TextView)layout.findViewById(R.id.hamcard_name3);

        coordinatorLayout = (CoordinatorLayout)layout.findViewById(R.id.coordinatorLayout);
        card_name = (TextView) layout.findViewById(R.id.card_name);

//        mMyHamRecyclerView = (RecyclerView) layout.findViewById(R.id.myham_list);
//        mMyHamlinearLayoutManager = new LinearLayoutManager(getActivity());
//        mMyHamlinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        mMyHamRecyclerView.setLayoutManager(mMyHamlinearLayoutManager);
//
//        mMyHamAdapter = new MyHamRecyclerAdapter(getActivity());
//        mMyHamAdapter.setClickListener(this);
//        mMyHamRecyclerView.setAdapter(mMyHamAdapter);


//        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(getActivity(), R.dimen.item_offset);
//        mMyHamRecyclerView.addItemDecoration(itemDecoration);

        RecyclerView recyclerView = (RecyclerView) layout.findViewById(R.id.my_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        HamRecyclerAdapter adapter = new HamRecyclerAdapter(getActivity());
        recyclerView.setAdapter(adapter);

        CircularOffsetDecoration circularDecoration = new CircularOffsetDecoration(getActivity(), R.dimen.circular_offset);
        recyclerView.addItemDecoration(circularDecoration);

        int[] colors = getResources().getIntArray(mColors[0]);

        mainLayout = (LinearLayout) layout.findViewById(R.id.main_layout);

        firstLayout = (LinearLayout) layout.findViewById(R.id.first_layout);
        firstLayout.setBackgroundColor(colors[0]);

        secondLayout = (LinearLayout) layout.findViewById(R.id.second_layout);
        secondLayout.setBackgroundColor(colors[1]);

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

        setHamcards();
// Inflate the layout for this fragment
        return layout;
    }

    @Override
    public void itemClick(View view, final int position) {
//        Card card = mMyHamAdapter.getItem(position);
//        if(position == mMyHamAdapter.getItemCount()-1) {
//            Toast.makeText(getActivity(), "카드등록화면 전환", Toast.LENGTH_SHORT).show();
//        } else {
//            this.position = position;
//            mainLayout.startAnimation(anim);
//
//            firstLayout.setBackgroundColor(card.getColors()[0]);
//            secondLayout.setBackgroundColor(card.getColors()[2]);
//            card_name.setText(card.getText());
//
//            firstLayout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(getActivity(), "화면 전환" + position, Toast.LENGTH_SHORT).show();
//                }
//            });
//
//            if(position == 1 || position == 3) {
//                snackbar = Snackbar.make(coordinatorLayout, card.getDueDate().getYear() + "년 " + card.getDueDate().getMonth() + "월 " + card.getDueDate().getDay() + "일" + "에 만료됩니다.", Snackbar.LENGTH_INDEFINITE)
//                        .setAction("연장", new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                Toast.makeText(getActivity(), "연장 화면", Toast.LENGTH_SHORT).show();
//                            }
//                        })
//                        .setActionTextColor(getResources().getColor(R.color.primary));
//
//                snackbar.show();
//
//            } else {
//                if(snackbar != null) {
//                    snackbar.dismiss();
//                }
//            }
//
//            mMyHamAdapter.notifyDataSetChanged();
//        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_myham, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().finish();
                break;

            case R.id.add_card:
                Dialog dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.setContentView(R.layout.show_barcode);
                dialog.setCanceledOnTouchOutside(true);

                ((ImageView) dialog.findViewById(R.id.show_barcode)).setImageBitmap(BarcodeManager.getInstance().getBitmapFromMemCache(barcode[mSelectedCard]));
                ((TextView) dialog.findViewById(R.id.show_barcode_number)).setText(barcode[mSelectedCard]);

                dialog.getWindow().getAttributes().width = WindowManager.LayoutParams.MATCH_PARENT;
                dialog.getWindow().getAttributes().gravity = Gravity.TOP;
                dialog.getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimation;

                dialog.show();
                break;

        }
        return true;
    }

    public void setHamcards(){
        for(int i = 0; i < hamCards.size(); i++) {
            imageCache.loadBitmap(hamCards.get(i).getImage(), hamcardImage[i]);
            hamcardName[i].setText(mStrings[i]);

            final int finalI = i;
            hamcardImage[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPreviouslySelectedCard = mSelectedCard;
                    mSelectedCard = finalI;

                    if(finalI == mResources.length) {
                        hamcardImage[mPreviouslySelectedCard].setAlpha(0.5f);
//                        hamcardImage[mSelectedCard].startAnimation(card_selected_animation);
                        intent = new Intent(getActivity(), MembershipGuideActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        getActivity().startActivity(intent);
                        getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.hold);
                    } else {
                        hamcardImage[mPreviouslySelectedCard].setAlpha(0.5f);
                        hamcardImage[mSelectedCard].setAlpha(1f);

                        mainLayout.startAnimation(anim);

                        firstLayout.setBackgroundColor(hamCards.get(finalI).getColors()[0]);
                        secondLayout.setBackgroundColor(hamCards.get(finalI).getColors()[2]);
                        card_name.setText(hamCards.get(finalI).getText());

                        firstLayout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getActivity(), "화면 전환" + mSelectedCard, Toast.LENGTH_SHORT).show();
                            }
                        });

                    }

                    if(finalI == 1) {
                        snackbar = Snackbar.make(coordinatorLayout, hamCards.get(finalI).getDueDate().getYear() + "년 " + hamCards.get(finalI).getDueDate().getMonth() + "월 " + hamCards.get(finalI).getDueDate().getDay() + "일" + "에 만료됩니다.", Snackbar.LENGTH_INDEFINITE)
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
            });

            if (mSelectedCard == i) {
                hamcardImage[i].setAlpha(1f);
            } else {
                hamcardImage[i].setAlpha(0.5f);
            }
        }
    }
}
