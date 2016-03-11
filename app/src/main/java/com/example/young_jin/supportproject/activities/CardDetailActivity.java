package com.example.young_jin.supportproject.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.example.young_jin.supportproject.R;
import com.example.young_jin.supportproject.adapter.DetailExpandableAdapter;
import com.example.young_jin.supportproject.models.CardDetail;
import com.example.young_jin.supportproject.models.CardDetailChid;
import com.example.young_jin.supportproject.models.Crime;
import com.example.young_jin.supportproject.singleton.CrimeLab;

import java.util.ArrayList;
import java.util.List;

public class CardDetailActivity extends AppCompatActivity {

    private RecyclerView mCardDetailRecyclerView;
    private ArrayList<CardDetail> mGroupList;
    private ArrayList<ArrayList<String>> mChildList;
    private ArrayList<String> mChildListContent;

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.hold, R.anim.slide_out_left);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        TextView toolbar_title = (TextView) toolbar.findViewById(R.id.toolbar_title);
        toolbar_title.setText(getSupportActionBar().getTitle());
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        CardDetailItemAdapter cardDetailItemAdapter = new CardDetailItemAdapter(this, generateCrimes());
//        cardDetailItemAdapter.setCustomParentAnimationViewId(R.id.parent_list_item_expand_arrow);
//        cardDetailItemAdapter.setParentClickableViewAnimationDefaultDuration();
//        cardDetailItemAdapter.setParentAndIconExpandOnClick(true);
//
//        mCardDetailRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        mCardDetailRecyclerView.setLayoutManager(linearLayoutManager);
//        mCardDetailRecyclerView.setAdapter(cardDetailItemAdapter);

        ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.detail_list);

        mGroupList = new ArrayList<CardDetail>();
        mChildList = new ArrayList<ArrayList<String>>();
        mChildListContent = new ArrayList<String>();

        mGroupList.add(new CardDetail("레이아웃", R.drawable.main_card_info_1));
        mGroupList.add(new CardDetail("주유리터당 20월 할인", R.drawable.main_card_info_1));
        mGroupList.add(new CardDetail("외부 자동세차 무료", R.drawable.main_card_info_2));
        mGroupList.add(new CardDetail("엔진오일 교환 무료 (연1회)", R.drawable.main_card_info_3));

        mChildListContent.add("1");
        mChildListContent.add("2");
        mChildListContent.add("3");

        mChildList.add(null);
        mChildList.add(mChildListContent);
        mChildList.add(mChildListContent);
        mChildList.add(mChildListContent);

        expandableListView.setAdapter(new DetailExpandableAdapter(this, mGroupList, mChildList));

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if(groupPosition == 0 ){
                    return true;
                }

                return false;
            }
        });


        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                return false;
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {

            }
        });

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {

            }
        });
    }

    private ArrayList<ParentObject> generateCrimes() {
        CrimeLab crimeLab = CrimeLab.get(this);
        List<Crime> crimes = crimeLab.getmCrimes();
        ArrayList<ParentObject> parentObjects = new ArrayList<>();
        for (Crime crime : crimes) {
            ArrayList<Object> childList = new ArrayList<>();
            childList.add(new CardDetailChid(crime.getmDate(), crime.ismSolved()));
            crime.setChildObjectList(childList);
            parentObjects.add(crime);
        }
        return parentObjects;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;

        }
        return true;
    }
}
