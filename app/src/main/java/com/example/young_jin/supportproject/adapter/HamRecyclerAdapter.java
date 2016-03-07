package com.example.young_jin.supportproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.young_jin.supportproject.R;
import com.example.young_jin.supportproject.models.Crime;
import com.example.young_jin.supportproject.singleton.CrimeLab;

import java.util.ArrayList;

public class HamRecyclerAdapter extends RecyclerView.Adapter<HamRecyclerAdapter.MyViewHolder> {

    private final LayoutInflater inflater;
    private ArrayList<Crime> data;
    private Context mContext;
    private ClickListener clickListener;

    public HamRecyclerAdapter(Context mContext) {
        inflater = LayoutInflater.from(mContext);
        this.data = CrimeLab.get(mContext).getmCrimes();
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.ham_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.progressBar.setMax(getItemCount());
        holder.progressBar.setProgress(position+1);

//        holder.title.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(activity, current.getmTItle() + " View Holder", Toast.LENGTH_SHORT).show();
//            }
//        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setPosition(position);
                return false;
            }
        });

    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ProgressBar progressBar;

        public MyViewHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar)itemView.findViewById(R.id.progressBar);

        }

        @Override
        public void onClick(View v) {

            if (clickListener != null) {
                clickListener.itemClick(v, getPosition());
            }
        }
    }

    public interface ClickListener {

        void itemClick(View view, int position);
    }

    public Crime getItem(int position) {
        return data.get(position);
    }

    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

}
