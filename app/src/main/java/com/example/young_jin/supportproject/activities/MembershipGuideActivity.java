package com.example.young_jin.supportproject.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.young_jin.supportproject.MyFragment;
import com.example.young_jin.supportproject.MyFragment2;
import com.example.young_jin.supportproject.R;
import com.example.young_jin.supportproject.adapter.MyAdapter;
import com.example.young_jin.supportproject.fragmnets.MembershipGuideFragment;

public class MembershipGuideActivity extends AppCompatActivity {

    private Button filter_distance;
    private Button filter_price;

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.hold, R.anim.slide_out_left);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_membership_guide);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        filter_distance = (Button) toolbar.findViewById(R.id.filter_distance);
        filter_price = (Button) toolbar.findViewById(R.id.filter_price);

        filter_distance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filter_distance.setBackgroundColor(getResources().getColor(R.color.icons));
                filter_distance.setTextColor(getResources().getColor(R.color.primary));

                filter_price.setBackgroundColor(getResources().getColor(R.color.primary));
                ;
                filter_price.setTextColor(getResources().getColor(R.color.icons));
            }
        });

        filter_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filter_distance.setBackgroundColor(getResources().getColor(R.color.primary));
                filter_distance.setTextColor(getResources().getColor(R.color.icons));

                filter_price.setBackgroundColor(getResources().getColor(R.color.icons));
                filter_price.setTextColor(getResources().getColor(R.color.primary));
            }
        });

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
        adapter.addFragment(new MembershipGuideFragment(), "포인트 활용형");
        adapter.addFragment(new MyFragment2().newInstance(), "제휴사 기프트형");
        adapter.addFragment(new MyFragment().newInstance(), "체크/신용카드형");
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_membership_guide, menu);
        return true;
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
