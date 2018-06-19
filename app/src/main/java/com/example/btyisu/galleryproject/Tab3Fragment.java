package com.example.btyisu.galleryproject;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.btyisu.galleryproject.Volley.MyGsonRequest;
import com.example.btyisu.galleryproject.Volley.MyVolley;
import com.example.btyisu.galleryproject.data.ApiResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

public class Tab3Fragment extends Fragment {
    private TextView textView;
    private Button button;
    private ImageView imageView;
    private final String server_url = "http://api.m.afreecatv.com/broad/a/items";

    public Tab3Fragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final RequestQueue requestQueue = MyVolley.getInstance(getActivity()).getRequestQueue();

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.tab3_fragment, container, false);

        textView = (TextView) rootView.findViewById(R.id.tab3_textview);
        button = (Button) rootView.findViewById(R.id.tab3_button);
        imageView = (ImageView) rootView.findViewById(R.id.imageView);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestContentData();
            }
        });
        return rootView;
    }
    protected void requestContentData(){
        final RequestQueue afRequestQueue = MyVolley.getInstance(getActivity()).getRequestQueue();

        MyGsonRequest<ApiResponse> myReq = new MyGsonRequest<ApiResponse>(this.getActivity(),
                Request.Method.POST,
                server_url,
                ApiResponse.class,
                networkSuccessListener(),
                networkErrorListener());

        afRequestQueue.add(myReq);
    }
    private Response.Listener<ApiResponse> networkSuccessListener(){
        final String TAG = "networkSuccesListner";
        return new Response.Listener<ApiResponse>() {
            @Override
            public void onResponse(ApiResponse response) {
                String result = null;
                    if (response != null) {
                        result = response.getData().getGroups().get(0).get(1).getTitle();
                    }
                    textView.setText(result);

            }
        };
    }

    private Response.ErrorListener networkErrorListener(){
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "network error", Toast.LENGTH_SHORT).show();
            }
        };
    }


}
