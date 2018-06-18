package com.example.btyisu.galleryproject.Volley;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.HashMap;
import java.util.Map;

public class MyVolley {
    public static String PATH_COOKIE="Cookie";
    private static MyVolley one;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;
    private final LruCache<String,Bitmap> cache = new LruCache<String,Bitmap>(20);
    private static Map<String, RequestQueue> mRequestQueueMap = new HashMap<String, RequestQueue>();

    private MyVolley(Context context){
        requestQueue = Volley.newRequestQueue(context);
        imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {

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

    public static MyVolley getInstance(Context context){
        if(one == null){
            one = new MyVolley(context);
        }
        return one;
    }

    public RequestQueue getRequestQueue(Context context, String cachePath){
//        RequestQueue requestQueue = mRequestQueueMap.get(cachePath);
//        getCookieManager().getCookieStore().removeAll();
//
//        if(requestQueue == null){
//            if(!TextUtils.equals(PATH_COOKIE,cachePath)){
//                requestQueue = Volley.newRequestQueue(context,cachePath);
//            }
//        }
        return requestQueue;
    }

    private static CookieManager mCookieManager = null;

    private static CookieManager getCookieManager() {
        if (mCookieManager == null) {
            mCookieManager = new CookieManager();
            CookieHandler.setDefault(mCookieManager);
        }
        return mCookieManager;

    }

    public ImageLoader getImageLoader(){
        return imageLoader;
    }
}
