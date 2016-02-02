package com.example.young_jin.supportproject;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Gallery;
import android.widget.TextView;

/**
 * Created by Young-Jin on 2016-02-02.
 */
public class MyFragment2 extends Fragment{

    private CustomGallery picGallery;
    private PicAdapter imgAdapt;

    public static MyFragment2 newInstance() {
        MyFragment2 fragment = new MyFragment2();
        return fragment;
    }

    public MyFragment2() {

// Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.my_fragment2, container, false);

        picGallery = (CustomGallery) layout.findViewById(R.id.gallery);
        //create a new adapter
        imgAdapt = new PicAdapter(getActivity());

//set the gallery adapter
        picGallery.setAdapter(imgAdapt);

// Inflate the layout for this fragment
        return layout;
    }

}
