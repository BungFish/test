package com.example.young_jin.supportproject.barcode;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.example.young_jin.supportproject.singleton.BarcodeManager;
import com.google.zxing.BarcodeFormat;

public class LoadBarcodeTask extends AsyncTask<String, Void, Bitmap> {

    private final BarcodeGenerator barcodeGenerator;

    public LoadBarcodeTask() {
        barcodeGenerator = new BarcodeGenerator();
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        String barcodekey = params[0];

        Bitmap barcodeBitmap = BarcodeManager.getInstance().getBitmapFromMemCache(barcodekey);
            if (barcodeBitmap == null) {
                barcodeBitmap = barcodeGenerator.barcodeImageCreate(barcodekey, BarcodeFormat.CODE_128, 1000, 300);
                BarcodeManager.getInstance().addBitmapToMemoryCache(barcodekey, barcodeBitmap);
                return barcodeBitmap;
            } else {
                return barcodeBitmap;
            }

    }
}
