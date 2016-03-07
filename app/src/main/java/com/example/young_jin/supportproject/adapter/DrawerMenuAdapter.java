package com.example.young_jin.supportproject.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.young_jin.supportproject.R;
import com.example.young_jin.supportproject.models.DrawerMenuItemModel;

import java.util.Collections;
import java.util.List;

/**
 * Created by Jin on 2015-06-04.
 */
public class DrawerMenuAdapter extends RecyclerView.Adapter<DrawerMenuAdapter.MyViewHolder> {

    private final LayoutInflater inflater;
    List<DrawerMenuItemModel> data = Collections.emptyList();
    private Activity activity;
    private ClickListener clickListener;

    public DrawerMenuAdapter(Activity activity, List<DrawerMenuItemModel> data) {
        inflater = LayoutInflater.from(activity);
        this.data = data;
        this.activity = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        DrawerMenuItemModel current = data.get(position);
        holder.title.setText(current.getTitle());
        holder.icon.setImageResource(current.getIconId());

        if(activity.getTitle().equals("구인메뉴")){
                holder.row.setBackgroundColor(activity.getResources().getColor(R.color.icons));
                holder.title.setTextColor(activity.getResources().getColor(R.color.primary));
                holder.icon.setColorFilter(activity.getResources().getColor(R.color.primary));
        }

//        holder.icon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context,"ddd", Toast.LENGTH_SHORT).show();
//
//            }
//        });


    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;
        ImageView icon;
        LinearLayout row;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.listText);
            icon = (ImageView) itemView.findViewById(R.id.listIcon);
            row = (LinearLayout) itemView.findViewById(R.id.row);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            if (clickListener != null) {
//                context.startActivity(new Intent(context, SubActivity.class));
                clickListener.itemClick(v, getPosition());
            }
        }
    }

    public interface ClickListener {

        public void itemClick(View view, int position);
    }
}
