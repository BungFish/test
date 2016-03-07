package com.example.young_jin.supportproject.adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.young_jin.supportproject.adapter.recycler_item_decoration.CircularOffsetDecoration;
import com.example.young_jin.supportproject.ImageCache;
import com.example.young_jin.supportproject.R;
import com.example.young_jin.supportproject.barcode.LoadBarcodeTask;
import com.example.young_jin.supportproject.singleton.BarcodeManager;

public class LoginCardPagerAdapter extends PagerAdapter {

    private final ImageCache imageCache;

    int[] mResources = {
            R.drawable.main_card_no_sm_wh,
            R.drawable.main_card_no_basic_wh,
            R.drawable.main_card_no_drive_wh
    };

    int[] mColors = {
            R.array.sm,
            R.array.basic,
            R.array.drive
    };

    String[] barcode = {
            "87344535311",
            "78762432423",
            "23402395343"
    };

    String[] card_names;
    Context mContext;
    LayoutInflater mLayoutInflater;

    private LoadBarcodeTask[] loadBarcodeTasks;

    public LoginCardPagerAdapter(Context context) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        imageCache = new ImageCache(context);
        loadBarcodeTasks= new LoadBarcodeTask[getCount()];

        card_names = mContext.getResources().getStringArray(R.array.card_names);

    }

    @Override
    public int getCount() {
        return mResources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = mLayoutInflater.inflate(R.layout.login_card_pager_item, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
        imageCache.loadBitmap(mResources[position], imageView);

//        TextView card_name = (TextView) itemView.findViewById(R.id.card_name);
//        card_name.setText(card_names[position]);

        int[] colors = mContext.getResources().getIntArray(mColors[position]);

        LinearLayout mainLayout = (LinearLayout) itemView.findViewById(R.id.main_layout);

        LinearLayout firstLayout = (LinearLayout) itemView.findViewById(R.id.first_layout);
        firstLayout.setBackgroundColor(colors[0]);

        LinearLayout secondLayout = (LinearLayout) itemView.findViewById(R.id.second_layout);
        secondLayout.setBackgroundColor(colors[1]);

        RecyclerView recyclerView = (RecyclerView) itemView.findViewById(R.id.my_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        HamRecyclerAdapter adapter = new HamRecyclerAdapter(mContext);
        recyclerView.setAdapter(adapter);

        CircularOffsetDecoration circularDecoration = new CircularOffsetDecoration(mContext, R.dimen.circular_offset);
        recyclerView.addItemDecoration(circularDecoration);

        ImageView barcodeImage = (ImageView) itemView.findViewById(R.id.barcode);
        barcodeImage.setImageDrawable(mContext.getResources().getDrawable(R.drawable.main_barcode));
        barcodeImage.setOnClickListener(new View.OnClickListener() {

            private Dialog dialog;

            @Override
            public void onClick(View v) {

                dialog = new Dialog(mContext);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.setContentView(R.layout.show_barcode);
                dialog.setCanceledOnTouchOutside(true);

                ((ImageView) dialog.findViewById(R.id.show_barcode)).setImageBitmap(BarcodeManager.getInstance().getBitmapFromMemCache(barcode[position]));
                ((TextView) dialog.findViewById(R.id.show_barcode_number)).setText(barcode[position]);

                dialog.getWindow().getAttributes().width = WindowManager.LayoutParams.MATCH_PARENT;
                dialog.getWindow().getAttributes().gravity = Gravity.TOP;
                dialog.getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimation;

                dialog.show();
            }
        });

//        loadingIcon = (ProgressView) itemView.findViewById(R.id.loading_icon);
//
//        if(loadBarcodeTasks[position] == null) {
//            loadBarcodeTasks[position] = new LoadBarcodeTask(mContext) {
//                @Override
//                protected void onPostExecute(Bitmap barcodeBitmap) {
//                    barcodeImage.setImageBitmap(barcodeBitmap);
//                    loadingIcon.setVisibility(View.GONE);
//                    barcodeImage.setVisibility(View.VISIBLE);
//                    barcodeImage.startAnimation(fadeInAnim);
//                }
//            };
//            loadBarcodeTasks[position].execute(null, null, null);
//        }

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
        if(loadBarcodeTasks[position] != null) {
            if (!loadBarcodeTasks[position].isCancelled()) {
                loadBarcodeTasks[position].cancel(true);
            }
        }
    }

}