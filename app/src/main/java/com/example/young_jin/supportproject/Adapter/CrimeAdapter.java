package com.example.young_jin.supportproject.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.young_jin.supportproject.R;
import com.example.young_jin.supportproject.models.Crime;
import com.example.young_jin.supportproject.singleton.CrimeLab;

import java.util.ArrayList;
/**
 * Created by Jin on 2015-06-04.
 */
public class CrimeAdapter extends RecyclerView.Adapter<CrimeAdapter.MyViewHolder> {

    private final LayoutInflater inflater;
    private ArrayList<Crime> data;
    private Activity activity;
    private ClickListener clickListener;

    public CrimeAdapter(Activity activity) {
        inflater = LayoutInflater.from(activity);
        this.data = CrimeLab.get(activity).getmCrimes();
        this.activity = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.crime_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Crime current = data.get(position);
        holder.title.setText(current.getmTItle());
        holder.date.setText(current.getmDate()+"");
        holder.solved.setChecked(current.ismSolved());

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

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener {

        private TextView title;
        private TextView date;
        private CheckBox solved;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.crime_title);
            date = (TextView) itemView.findViewById(R.id.crime_date);
            solved = (CheckBox) itemView.findViewById(R.id.crime_solved_CheckBox);
            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);

        }

        @Override
        public void onClick(View v) {

            if (clickListener != null) {
                clickListener.itemClick(v, getPosition());
            }
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

            menu.setHeaderTitle(getItem(getPosition()).getmTItle());
            menu.add(0, R.id.menu_item_delete_crime, 0, R.string.delete_crime);//groupId, itemId, order, title
            menu.add(0, v.getId(), 0, "돌아가기");
        }
    }

    public interface ClickListener {

        public void itemClick(View view, int position);
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
