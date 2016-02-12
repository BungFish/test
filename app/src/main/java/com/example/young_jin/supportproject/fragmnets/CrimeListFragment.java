package com.example.young_jin.supportproject.fragmnets;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.young_jin.supportproject.adapter.CrimeAdapter;
import com.example.young_jin.supportproject.R;
import com.example.young_jin.supportproject.models.Crime;
import com.example.young_jin.supportproject.singleton.CrimeLab;

/**
 * Created by Young-Jin on 2016-02-09.
 */
public class CrimeListFragment extends Fragment implements CrimeAdapter.ClickListener {

    private RecyclerView recyclerView;
    private CrimeAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private static CrimeListFragment fragment;

    public static CrimeListFragment newInstance() {
        if(fragment==null) {
            fragment = new CrimeListFragment();
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getRetainInstance();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime_list, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.crime_list);
        adapter = new CrimeAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter.setClickListener(this);

        registerForContextMenu(recyclerView);

        return v;
    }

    @Override
    public void itemClick(View view, int position) {
        Toast.makeText(getActivity(), adapter.getItem(position).getmTItle(), Toast.LENGTH_SHORT).show();
    }

//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        getActivity().getMenuInflater().inflate(R.menu.crime_list_item_context, menu);
//    }
//
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Crime crime = adapter.getItem(adapter.getPosition());

        switch (item.getItemId()){
            case R.id.menu_item_delete_crime:
                CrimeLab.get(getActivity()).deleteCrime(crime);
                adapter.notifyDataSetChanged();
                return true;
        }
        return super.onContextItemSelected(item);
    }

}
