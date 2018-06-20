package com.example.btyisu.galleryproject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.example.btyisu.galleryproject.Volley.MyGsonRequest;
import com.example.btyisu.galleryproject.Volley.MyVolley;
import com.example.btyisu.galleryproject.data.ApiResponse;

import java.util.ArrayList;

public class Tab4Fragment extends Fragment {
    private final String server_url = "http://api.m.afreecatv.com/station/video/section/a/items2";
    private ImageLoader imageLoader;
    private Activity activity;
    private RecyclerView recyclerView;
    private NetRecyclerAdapter recyclerAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<String> dataSet = new ArrayList<>();
    int imageSize = 700;

    public Tab4Fragment(){}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof Activity){
            activity = (Activity) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        requestContentData();
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.tab4_fragment, container, false);
        initView(rootView);

        return rootView;
    }

    private void initView(View view){
        recyclerView = (RecyclerView) view.findViewById(R.id.tab4_recycler_view);
        recyclerView.setHasFixedSize(true); // to improve performance if you know that changes.
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL));
        recyclerAdapter = new NetRecyclerAdapter(activity);
        recyclerView.setAdapter(recyclerAdapter);

        int deviceWidth = getResources().getDisplayMetrics().widthPixels;
        Log.d("디바이스",String.valueOf(deviceWidth));
        int spanCount = deviceWidth/imageSize;
        int space = (deviceWidth - (imageSize*spanCount))/(spanCount*2);
        layoutManager = new GridLayoutManager(getActivity(),spanCount);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new SpacesItemDecoration(space));
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
                ArrayList<String> str = new ArrayList<>();
                    if (response != null) {
                        int count = response.getData().getGroups().get(0).size();
                        for(int i=0; i< count;i++) {
//                           dataSet.add(response.getData().getGroups().get(0).get(i).getThumbnail());
                            recyclerAdapter.dataAdd(i,response.getData().getGroups().get(0).get(i).getThumbnail());
                            recyclerAdapter.notifyItemInserted(i);
                        }
                        Log.d("groups",String.valueOf(response.getData().getGroups().size()));

                    }
            }
        };
    }

    private Response.ErrorListener networkErrorListener(){
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(activity, "network error", Toast.LENGTH_SHORT).show();
            }
        };
    }


}
