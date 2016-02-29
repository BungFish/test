package com.example.young_jin.supportproject.fragmnets;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.young_jin.supportproject.DatePickerFragment;
import com.example.young_jin.supportproject.R;
import com.example.young_jin.supportproject.models.Crime;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Young-Jin on 2016-02-09.
 */
public class CrimeAddFragment extends Fragment {

    private EditText mTitleField;
    private Button mDateButton;
    private Crime mCrime;
    private CheckBox mSolvedCheckBox;
    private static CrimeAddFragment fragment;
    private static final int REQUEST_DATE = 0;
    private Calendar myCalendar;

    public static CrimeAddFragment newInstance() {
        if(fragment==null) {
            fragment = new CrimeAddFragment();
        }
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCrime = new Crime();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_crime, container, false);

        mTitleField = (EditText) v.findViewById(R.id.crime_title);
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCrime.setmTItle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        myCalendar = Calendar.getInstance();
        myCalendar.setTime(mCrime.getmDate());

        String show = "yyyy년 MM월 dd일"; //In which you need put here
        SimpleDateFormat showFormat = new SimpleDateFormat(show, Locale.KOREA);

        mDateButton = (Button) v.findViewById(R.id.crime_date);
        mDateButton.setText(showFormat.format(myCalendar.getTime()));

        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, monthOfYear);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        String show = "yyyy년 MM월 dd일"; //In which you need put here
                        SimpleDateFormat showFormat = new SimpleDateFormat(show, Locale.KOREA);

                        String save = "yyyy/MM/dd"; //In which you need put here
                        SimpleDateFormat saveFormat = new SimpleDateFormat(save, Locale.KOREA);

                        mDateButton.setText(showFormat.format(myCalendar.getTime()));
                    }

                }, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        mSolvedCheckBox = (CheckBox) v.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCrime.setmSolved(isChecked);
            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
       if(requestCode != Activity.RESULT_OK) return;
        if(requestCode == REQUEST_DATE){
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mCrime.setmDate(date);
            mDateButton.setText(mCrime.getmDate().toString());
        }
    }
}
