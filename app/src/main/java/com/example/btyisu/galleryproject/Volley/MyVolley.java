package com.example.btyisu.galleryproject.Volley;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class MyVolley {
    private static MyVolley one;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;
    private Context context;
    private MyVolley(Context context) {
        this.context = context;
        requestQueue = Volley.newRequestQueue(context);
    }

    public static MyVolley getInstance(Context context) {
        if (one == null) {
            one = new MyVolley(context);
        }
        return one;
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public ImageLoader getImageLoader() {

        ImageLoader imageLoader = new ImageLoader(getRequestQueue(), new ImageLoader.ImageCache() {
        private final LruCache<String,Bitmap> mCache = new LruCache<String, Bitmap>(4*1024*1024);
            @Override
            public Bitmap getBitmap(String url) {
                return mCache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                mCache.put(url, bitmap);
            }
        });
        return imageLoader;
    }

}