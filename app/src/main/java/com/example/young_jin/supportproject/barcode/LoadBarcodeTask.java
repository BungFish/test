package com.example.young_jin.supportproject.barcode;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.example.young_jin.supportproject.singleton.BarcodeManager;
import com.google.zxing.BarcodeFormat;

/**
 * Created by Young-Jin on 2016-03-03.
 */
public class LoadBarcodeTask extends AsyncTask<String, Void, Bitmap> {

    private final BarcodeGenerator barcodeGenerator;
    private String barcodekey;
    private Bitmap barcodeBitmap;

    public LoadBarcodeTask() {
        barcodeGenerator = new BarcodeGenerator();
    }

    @Override
    protected Bitmap doInBackground(String... params) {
            barcodekey = params[0];

            barcodeBitmap = BarcodeManager.getInstance().getBitmapFromMemCache(barcodekey);
            if (barcodeBitmap == null) {
                barcodeBitmap = barcodeGenerator.barcodeImageCreate(barcodekey, BarcodeFormat.CODE_128, 1000, 300);
                BarcodeManager.getInstance().addBitmapToMemoryCache(barcodekey, barcodeBitmap);
                return barcodeBitmap;
            } else {
                return barcodeBitmap;
            }

    }
}
