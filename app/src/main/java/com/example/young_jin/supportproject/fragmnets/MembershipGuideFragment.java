package com.example.young_jin.supportproject.fragmnets;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.young_jin.supportproject.adapter.recycler_item_decoration.ItemOffsetDecoration;
import com.example.young_jin.supportproject.R;
import com.example.young_jin.supportproject.adapter.BigHamRecyclerAdapter;

public class MembershipGuideFragment extends Fragment {

    public static MembershipGuideFragment newInstance() {
        return new MembershipGuideFragment();
    }

    public MembershipGuideFragment() {

// Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_membership_guide, container, false);

        RecyclerView recyclerView = (RecyclerView) layout.findViewById(R.id.myham_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        BigHamRecyclerAdapter adapter = new BigHamRecyclerAdapter(getActivity());
        recyclerView.setAdapter(adapter);

        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(getActivity(), R.dimen.membership_offset);
        recyclerView.addItemDecoration(itemDecoration);

        return layout;

    }
}
