package com.example.btyisu.galleryproject.Volley;

import android.content.Context;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;

public class MyGsonRequest<T> extends Request<T> {
    private final Context mContext;
    private final Gson mGson = new Gson();
    private final Class<T> mClazz;
    private final Listener<T> mListener;

    /**
     * Make a GET request and return a parsed object from JSON.
     *
     * @param url URL of the request to make
     * @param clazz Relevant class object, for Gson's reflection
     * @param headers Map of request headers
     */

    public MyGsonRequest(Context context, int method, String url, Class<T> clazz, Listener<T> listener, Response.ErrorListener errorListener){
        super(method, url, errorListener);
            this.mClazz = clazz;
            this.mListener = listener;
            this.mContext = context;
    }
    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try{
            if(response == null){
                return null;
            }
            String json = new String(response.data);
            return Response.success(mGson.fromJson(json, mClazz), HttpHeaderParser.parseCacheHeaders(response));
        }catch (JsonSyntaxException e){
            return Response.error(new ParseError(e));
        }
    }
}
