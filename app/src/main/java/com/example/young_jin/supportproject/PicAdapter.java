package com.example.young_jin.supportproject;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by Young-Jin on 2016-02-02.
 */
public class PicAdapter extends BaseAdapter{
    //use the default gallery background image
    int defaultItemBackground;

    int[] mResources = {
            R.drawable.android_wallpaper,
            R.drawable.android_wallpaper2,
            R.drawable.android_wallpaper,
            R.drawable.android_wallpaper2,
    };

    //gallery context
    private Context galleryContext;

    //array to store bitmaps to display
    private Bitmap[] imageBitmaps;

    //placeholder bitmap for empty spaces in gallery
    Bitmap placeholder;

    public PicAdapter(Context c) {

        //instantiate context
        galleryContext = c;

        //create bitmap array
        imageBitmaps  = new Bitmap[mResources.length];

        //decode the placeholder image
//        placeholder = BitmapFactory.decodeResource(c.getResources(), R.drawable.android_wallpaper);

        //more processing
        for(int i=0; i<imageBitmaps.length; i++) {
            imageBitmaps[i] = decodeSampledBitmapFromResource(c.getResources() ,mResources[i], 100, 100);

        }

        //get the styling attributes - use default Andorid system resources
        TypedArray styleAttrs = galleryContext.obtainStyledAttributes(R.styleable.PicGallery);

//get the background resource
//        for(int i=0; i<imageBitmaps.length; i++)
//        mResources[i] = styleAttrs.getResourceId(R.styleable.PicGallery_android_galleryItemBackground, 0);

//recycle attributes
        styleAttrs.recycle();
    }

    @Override
    public int getCount() {
        return imageBitmaps.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //create the view
        ImageView imageView = new ImageView(galleryContext);
        //specify the bitmap at this position in the array
        imageView.setImageBitmap(imageBitmaps[position]);
        //set layout options
        imageView.setLayoutParams(new Gallery.LayoutParams(1000, 1000));
        //scale type within view area
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        //set default gallery item background
        imageView.setBackgroundResource(mResources[position]);

        //return the view
        return imageView;
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }
}
