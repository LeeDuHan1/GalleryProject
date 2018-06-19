//package com.example.btyisu.galleryproject.Volley;
//
//import android.content.*;
//
//import com.google.gson.Gson;
//import com.google.gson.JsonSyntaxException;
//import com.android.volley.*;
//import com.android.volley.Response.ErrorListener;
//import com.android.volley.Response.Listener;
//import com.android.volley.toolbox.HttpHeaderParser;
//
//import kr.co.nowcom.core.utils.NLog;
//import kr.co.nowcom.mobile.afreeca.common.manager.AfCookieManager;
//
//public class GsonRequest<T> extends AfRequest<T> {
//    protected final Gson mGson;
//    protected final Class<T> mClazz;
//    protected final Listener<T> mListener;
//    protected final Context mContext;
//
//
//    public GsonRequest(Context context,
//                       int method,
//                       String url,
//                       Class<T> clazz,
//                       Listener<T> listener,
//                       ErrorListener errorListener) {
//        super(context ,method, url, errorListener);
//        this.mClazz = clazz;
//        this.mListener = listener;
//        mGson = new Gson();
//        mContext = context;
//    }
//
////    public GsonRequest(Context context,
////                       int method,
////                       String url,
////                       Class<T> clazz,
////                       Listener<T> listener,
////                       ErrorListener errorListener,
////                       DefaultRetryPolicy retryPolicy) {
////        super(context ,method, url, errorListener, retryPolicy);
////        this.mClazz = clazz;
////        this.mListener = listener;
////        mGson = new Gson();
////        mContext = context;
////    }
//
//
//    public GsonRequest(Context context,
//                       int method,
//                       String url,
//                       Class<T> clazz,
//                       Listener<T> listener,
//                       ErrorListener errorListener,
//                       Gson gson) {
//        super(context, method, url, errorListener);
//        this.mClazz = clazz;
//        this.mListener = listener;
//        mGson = gson;
//        mContext = context;
//    }
//
//
//    @Override
//    protected void deliverResponse(T response) {
//        mListener.onResponse(response);
//    }
//
//
//    @Override
//    protected Response<T> parseNetworkResponse(NetworkResponse response) {
//        try {
//            if(response == null){
//                return null;
//            }
//            //로그인 쿠키 저장 로직 호출
//            AfCookieManager.saveCookieString(mContext, response.allHeader);
//            /**
//             * String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
//             * 기존 구현된 소스 한글 깨짐으로 아래로 바꿈 이유가 있을건데
//             * String json = new String(response.data);
//             */
//
//            String json = new String(response.data);
//            return Response.success(mGson.fromJson(json, mClazz),
//                    HttpHeaderParser.parseCacheHeaders(response));
//        } catch (JsonSyntaxException e) {
//            return Response.error(new ParseError(e));
//        }
//    }
//}