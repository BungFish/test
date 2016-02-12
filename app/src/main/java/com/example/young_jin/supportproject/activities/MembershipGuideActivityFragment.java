package com.example.young_jin.supportproject.activities;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.young_jin.supportproject.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class MembershipGuideActivityFragment extends Fragment {

    public MembershipGuideActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_membership_guide, container, false);
    }
}
