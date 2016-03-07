package com.example.young_jin.supportproject.singleton;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

public class BarcodeManager {
    private static BarcodeManager ourInstance = new BarcodeManager();
    private final LruCache<String, Bitmap> mMemoryCache;

    public static BarcodeManager getInstance() {
        return ourInstance;
    }

    private BarcodeManager() {
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

        // Use 1/8th of the available memory for this memory cache.
        final int cacheSize = maxMemory / 8;

        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                // The cache size will be measured in kilobytes rather than
                // number of items.
                return bitmap.getByteCount() / 1024;
            }
        };
    }

    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    public Bitmap getBitmapFromMemCache(String key) {
        return mMemoryCache.get(key);
    }

}
