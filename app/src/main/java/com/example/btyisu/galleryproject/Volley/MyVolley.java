package com.example.btyisu.galleryproject.Volley;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class MyVolley {
    private static MyVolley one;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;
    private final LruCache<String,Bitmap> cache = new LruCache<String,Bitmap>(20);

    private MyVolley(Context context) {
        requestQueue = Volley.newRequestQueue(context);
        imageLoader = new ImageLoader(requestQueue,new ImageLoader.ImageCache(){

            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url,bitmap);
            }
        });

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

//    public ImageLoader getImageLoader() {
//        return imageLoader;
//    }
    public ImageLoader getImageLoader() {
        ImageLoader imageLoader = new ImageLoader(getRequestQueue(), new ImageLoader.ImageCache() {
        private final LruCache<String,Bitmap> mCache = new LruCache<String, Bitmap>(20);
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