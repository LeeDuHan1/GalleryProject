//package com.example.btyisu.galleryproject.Volley;
//
//import android.app.ActivityManager;
//import android.content.Context;
//import android.os.Build;
//import android.text.TextUtils;
//
//import com.android.volley.RequestQueue;
//import com.android.volley.toolbox.HttpClientStack;
//import com.android.volley.toolbox.ImageLoader;
//import com.android.volley.toolbox.Volley;
//
//import org.apache.http.impl.client.AbstractHttpClient;
//import org.apache.http.impl.client.DefaultHttpClient;
//
//import java.net.CookieHandler;
//import java.net.CookieManager;
//import java.util.HashMap;
//import java.util.Map;
//
//import kr.co.nowcom.core.utils.NDeviceUtils;
//
//
///**
// * Helper class that is used to provide references to initialized RequestQueue(s) and ImageLoader(s)
// *
// * @author Ognyan Bankov
// *
// */
//public class AfVolley {
//
//    public static String PATH_CACHE_CONTENT_LIST_IMAGE_SEARCH =  "ContentListImageSearch";
//    public static String PATH_CACHE_CONTENT_LIST_IMAGE_RECENT_BROADCAST = "ContentListImageRecentBroadcast";
//    public static String PATH_CACHE_CONTENT_LIST_IMAGE_HOME = "ContentListImageHome";
//    public static String PATH_CACHE_CONTENT_LIST_IMAGE_FAVORITE = "ContentListImageFavorite";
//    public static String PATH_CACHE_CONTENT_LIST_IMAGE_RANK = "ContentListImageRank";
//    public static String PATH_CACHE_CONTENT_LIST_IMAGE_ANIMATION = "ContentListImageAnimation";
//    public static String PATH_CACHE_CONTENT_LIST_IMAGE_VOD = "ContentListImageVod";
//    public static String PATH_CACHE_CONTENT_LIST_IMAGE_LIVE = "ContentListImageLive";
//    public static String PATH_CACHE_CONTENT_LIST_IMAGE_UCC = "ContentListImageUCC";
//    public static String PATH_CACHE_CONTENT_LIST_IMAGE_CACHE = "ContentListImageCache";
//    public static String PATH_NO_CACHE="NoCache";
//    public static String PATH_CACHE_CONTENT_IMAGE="ContentImage";
//    public static String PATH_CACHE_MAIN_MENT_IMAGE="MainMenuImage";
//    public static String PATH_CACHE_CATEGORY_IMAGE="CategotyMenuImage";
//    public static String PATH_CACHE_RELATION_BJ_IMAGE="RelationBjImage";
//    public static String PATH_CACHE_GIFT_IMAGE="GiftImage";
//    public static String PATH_CACHE_CHROMECAST_IMAGE="ChromeCastImage";
//    public static String PATH_CACHE_APP_ADVER_IMAGE="AppAdverImage";
//    //    public static String PATH_CACHE_PLAYER_GALLERY_THUMB_IMAGE="PlayerGalleryThumbImage";
//    public static String PATH_CACHE_VOD_ADVER_BANNER_IMAGE="VodAdverBannerImage";
//    public static String PATH_CACHE_VOD_ARCHIVE_THUMB_IMAGE="VodArchiveThumbImage";
//    public static String PATH_CACHE_CHOCOSLOT = "ChocolateSlotImage";
//    public static String PATH_CACHE_LIVE_CATEGORY_IMAGE="LiveCategotyImage";
//    public static String PATH_CACHE_DEFAULT="default";
//    public static String PATH_COOKIE="Cookie";
//    public static String PATH_CACHE_MULTIBROAD_IMAGE="MultiBroadImg";
//
//    private static Map<String, RequestQueue> mRequestQueueMap = new HashMap<String, RequestQueue>();
//    private static Map<String, ImageLoader> mImageLoaderMap = new HashMap<String, ImageLoader>();
//    private static BitmapLruCache mBitmapLruNoCache;
//    private static BitmapLruCache mBitmapLruCache;
//    private static BitmapLruCache mBitmapLruCategoryCache;
//    private static BitmapLruCache mBitmapLruNoclearCache;
//    private static AbstractHttpClient mHttpClient;
//
//    //  private static ImageLoader mImageLoader1;
//    // private static ImageLoader mImageLoader2;
//
//    public static RequestQueue getRequestQueue(Context context, String cachePath) {
//
//        RequestQueue requestQueue = mRequestQueueMap.get(cachePath);
//        getCookieManager().getCookieStore().removeAll();
//
//        if (requestQueue == null) {
//            if(TextUtils.equals(PATH_COOKIE, cachePath)){
//                mHttpClient = null;
//                mHttpClient = new DefaultHttpClient();
//                requestQueue = Volley.newRequestQueue(context, new HttpClientStack(mHttpClient), PATH_NO_CACHE);
//                mRequestQueueMap.put(cachePath, requestQueue);
//            } else {
//                requestQueue = Volley.newRequestQueue(context, cachePath);
//                mRequestQueueMap.put(cachePath, requestQueue);
//            }
//        }
//        return requestQueue;
//
//    }
//
//    private static CookieManager mCookieManager = null;
//
//    private static CookieManager getCookieManager() {
//        if (mCookieManager == null) {
//            mCookieManager = new CookieManager();
//            CookieHandler.setDefault(mCookieManager);
//        }
//        return mCookieManager;
//
//    }
//
//    /**
//     * Returns instance of ImageLoader initialized with {@see FakeImageCache} which effectively means
//     * that no memory caching is used. This is useful for images that you know that will be show
//     * only once.
//     *
//     * @return
//     */
//    public static ImageLoader getImageLoader(Context context, String cachePath) {
//        if(TextUtils.isEmpty(cachePath)){
//            cachePath = PATH_CACHE_DEFAULT;
//        }
//        if(mBitmapLruNoCache == null){
//            mBitmapLruNoCache = new BitmapLruCache(1);
//        }
//
//        if(mBitmapLruCache == null){
//            int memClass = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass();
//            // Use 1/8th of the available memory for this memory cache.
//
//            int cacheSize = 1024 * 1024 * 10;
//            if(NDeviceUtils.getSdkInt() >= Build.VERSION_CODES.LOLLIPOP) {
//                cacheSize = 1024 * 1024 * memClass / 8;
//            }
//
//            mBitmapLruCache = new BitmapLruCache(cacheSize);
//        }
//
//        if(mBitmapLruCategoryCache == null){
//
//            int cacheSize = 1024 * 512 ;
//            if(NDeviceUtils.getSdkInt() >= Build.VERSION_CODES.LOLLIPOP) {
//                cacheSize = 1024 * 1024 ;
//            }
//            mBitmapLruCategoryCache = new BitmapLruCache(cacheSize);
//        }
//
//        if(mBitmapLruNoclearCache == null){
//
//            int cacheSize = 1024 * 1024 * 4;
//            if(NDeviceUtils.getSdkInt() >= Build.VERSION_CODES.LOLLIPOP) {
//                cacheSize = 1024 * 1024 * 8;
//            }
//            mBitmapLruNoclearCache = new BitmapLruCache(cacheSize);
//        }
//
//
//
//
//        ImageLoader imageLoader = mImageLoaderMap.get(cachePath);
//
//        if (imageLoader == null) {
//            RequestQueue requestQueue = getRequestQueue(context, cachePath);
//
//            if(TextUtils.equals(cachePath, AfVolley.PATH_NO_CACHE)){
//                imageLoader = new ImageLoader(requestQueue, mBitmapLruNoCache);
//            } else if(TextUtils.equals(cachePath, AfVolley.PATH_CACHE_LIVE_CATEGORY_IMAGE)){
//                imageLoader = new ImageLoader(requestQueue, mBitmapLruCategoryCache);
//            } else if(TextUtils.equals(cachePath, AfVolley.PATH_CACHE_CONTENT_LIST_IMAGE_CACHE)){
//                imageLoader = new ImageLoader(requestQueue, mBitmapLruNoclearCache);
//            } else {
//                imageLoader = new ImageLoader(requestQueue, mBitmapLruCache);
//            }
//
//            mImageLoaderMap.put(cachePath, imageLoader);
//        }
//        return imageLoader;
//    }
//
//    public static void clearCache(Context context, String cachePath) {
//        if(cachePath == null){
//            if(mBitmapLruNoCache != null){
//                mBitmapLruNoCache.evictAll();
//            }
//
//            if(mBitmapLruCache != null){
//                mBitmapLruCache.evictAll();
//            }
//
//            for( String key : mRequestQueueMap.keySet() ){
//                RequestQueue requestQueue = mRequestQueueMap.get(key);
//                if(requestQueue!=null
//                        && requestQueue.getCache()!=null
//                        && !TextUtils.equals(key, AfVolley.PATH_CACHE_LIVE_CATEGORY_IMAGE)
//                        && !TextUtils.equals(key, AfVolley.PATH_CACHE_CONTENT_LIST_IMAGE_CACHE)){
//                    requestQueue.getCache().clear();
//                }
//            }
//
//        } else {
//            if(TextUtils.equals(cachePath, AfVolley.PATH_NO_CACHE)){
//                if(mBitmapLruNoCache != null){
//                    mBitmapLruNoCache.evictAll();
//                }
//            } else if(TextUtils.equals(cachePath, AfVolley.PATH_CACHE_LIVE_CATEGORY_IMAGE)){
//                if(mBitmapLruCategoryCache != null){
//                    mBitmapLruCategoryCache.evictAll();
//                }
//            } else {
//                if(mBitmapLruCache != null){
//                    mBitmapLruCache.evictAll();
//                }
//            }
//
//            RequestQueue requestQueue = mRequestQueueMap.get(cachePath);
//            if(requestQueue!=null &&
//                    requestQueue.getCache()!=null){
//                requestQueue.getCache().clear();
//            }
//        }
//    }
//
//    public static AbstractHttpClient getHttpClient(String cachePath) {
//        if(TextUtils.equals(PATH_COOKIE, cachePath)){
//            return mHttpClient;
//        } else {
//            return null;
//        }
//    }
//
//}
