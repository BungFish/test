package com.example.young_jin.supportproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.example.young_jin.supportproject.R;
import com.example.young_jin.supportproject.models.CardDetailChid;
import com.example.young_jin.supportproject.models.Crime;

import java.util.List;

/**
 * Created by Young-Jin on 2016-03-07.
 */
public class CardDetailItemAdapter extends ExpandableRecyclerAdapter<CardDetailItemAdapter.CardDetailItemParentViewHolder, CardDetailItemAdapter.CardDetailItemChildViewHolder> {

    private final LayoutInflater mInflater;

    public CardDetailItemAdapter(Context context, List<ParentObject> parentItemList) {
        super(context, parentItemList);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public CardDetailItemParentViewHolder onCreateParentViewHolder(ViewGroup viewGroup) {
        View view = mInflater.inflate(R.layout.list_item_card_detail_parent, viewGroup, false);
        return new CardDetailItemParentViewHolder(view);
    }

    @Override
    public CardDetailItemChildViewHolder onCreateChildViewHolder(ViewGroup viewGroup) {
        View view = mInflater.inflate(R.layout.list_item_card_detail_child, viewGroup, false);
        return new CardDetailItemChildViewHolder(view);
    }

    @Override
    public void onBindParentViewHolder(CardDetailItemParentViewHolder cardDetailItemParentViewHolder, int i, Object parentObject) {
        Crime crime = (Crime) parentObject;
        cardDetailItemParentViewHolder.mCrimeTitleTextView.setText(crime.getmTItle());
    }

    @Override
    public void onBindChildViewHolder(CardDetailItemChildViewHolder cardDetailItemChildViewHolder, int i, Object childObject) {
        CardDetailChid crimeChild = (CardDetailChid) childObject;
        cardDetailItemChildViewHolder.mCrimeDateText.setText(crimeChild.getmDate().toString());
        cardDetailItemChildViewHolder.mCrimeSolvedCheckBox.setChecked(crimeChild.ismSolved());

    }

    class CardDetailItemParentViewHolder extends ParentViewHolder {

        public TextView mCrimeTitleTextView;
        public ImageButton mParentDropDownArrow;

        public CardDetailItemParentViewHolder(View itemView) {
            super(itemView);

            mCrimeTitleTextView = (TextView) itemView.findViewById(R.id.parent_list_item_crime_title_text_view);
//            mParentDropDownArrow = (ImageButton) itemView.findViewById(R.id.parent_list_item_expand_arrow);
        }
    }

    class CardDetailItemChildViewHolder extends ChildViewHolder {

        public TextView mCrimeDateText;
        public CheckBox mCrimeSolvedCheckBox;

        public CardDetailItemChildViewHolder(View itemView) {
            super(itemView);

            mCrimeDateText = (TextView) itemView.findViewById(R.id.child_list_item_crime_date_text_view);
//            mCrimeSolvedCheckBox = (CheckBox) itemView.findViewById(R.id.child_list_item_crime_solved_check_box);
        }
    }
}
