package com.example.young_jin.supportproject.barcode;

import android.graphics.Bitmap;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

public class BarcodeGenerator {

    private static final int BLACK = 0xFF000000;
    private static final int White = 0xFFFFFFFF;

    public BarcodeGenerator(){
    }

    public Bitmap barcodeImageCreate(String contents, BarcodeFormat format, int mImgWidth, int mImgHeight) {
        MultiFormatWriter writer = new MultiFormatWriter();
        BitMatrix bitMatrix;
        try {
            bitMatrix = writer.encode(contents, format, mImgWidth, mImgHeight);
            return makeBitmap(bitMatrix);
        } catch (IllegalArgumentException iae) {
            // Unsupported format
            return null;
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Bitmap makeBitmap(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        Bitmap image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        int x, y;

        for (x = 0; x < width; x++) {
            for (y = 0; y < height; y++) {
                image.setPixel(x, y, matrix.get(x, y) ? BLACK : White);
            }
        }
        return image;
    }
}
