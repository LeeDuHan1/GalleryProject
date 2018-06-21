package com.example.btyisu.galleryproject;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.media.Image;
import android.net.Uri;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.example.btyisu.galleryproject.Volley.MyGsonRequest;
import com.example.btyisu.galleryproject.Volley.MyVolley;
import com.example.btyisu.galleryproject.data.ApiResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Tab3Fragment extends Fragment {
    private final String server_url = "http://api.m.afreecatv.com/broad/a/items2";
    private ImageLoader imageLoader;
    private Activity activity= null;
    private RecyclerView recyclerView = null;
    private NetRecyclerAdapter recyclerAdapter = null;
    private GridLayoutManager layoutManager= null;
    private SpacesItemDecoration spacesItemDecoration = null;
    private RequestQueue afRequestQueue = null;
    private int imageSize = 700;
    public int page = 1;

    public Tab3Fragment(){}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof Activity){
            activity = (Activity) context;
        }
        Toast.makeText(getActivity(),"onAttach", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setImageCount();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layoutManager = new GridLayoutManager(getActivity(),2);
        afRequestQueue = MyVolley.getInstance(getActivity()).getRequestQueue();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        requestContentData();
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.tab3_fragment, container, false);
        initView(rootView);

        setRetainInstance(true);
        Toast.makeText(getActivity(),"onCreateView", Toast.LENGTH_SHORT).show();
        return rootView;
    }

    private void initView(View view){
        recyclerView = (RecyclerView) view.findViewById(R.id.tab3_recycler_view);
        recyclerView.setHasFixedSize(true); // to improve performance if you know that changes.
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL));
        recyclerAdapter = new NetRecyclerAdapter(activity,R.layout.content_live_grid_view,false);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                GridLayoutManager gridLayoutManager = ((GridLayoutManager)recyclerView.getLayoutManager());
                int lastViewItem = gridLayoutManager.findLastVisibleItemPosition()+1;
                Log.d("last",String.valueOf(lastViewItem));
                Log.d("adpaterCount",String.valueOf(recyclerAdapter.getItemCount()));
                if(recyclerAdapter.getItemCount() == lastViewItem){
                    Log.d("발동","했으요");
                    page += 1;
                    requestContentData();
                }
            }
        });
        setImageCount();
    }

    private void setImageCount(){
        int deviceWidth = getResources().getDisplayMetrics().widthPixels;
        Log.d("디바이스크기 : ",String.valueOf(deviceWidth));
        int spanCount = deviceWidth/imageSize;
        int space = (deviceWidth - (imageSize*spanCount))/(spanCount*2);
        layoutManager.setSpanCount(spanCount);
        recyclerView.setLayoutManager(layoutManager);
        spacesItemDecoration = new SpacesItemDecoration(space);
        Log.d("데코카운트",String.valueOf(recyclerView.getItemDecorationCount()));
//        int count = recyclerView.getItemDecorationCount();
//        for(int i = 0; i<count; i++){
        if(recyclerView.getItemDecorationCount()>2){
            recyclerView.removeItemDecorationAt(2);
        }

        recyclerView.addItemDecoration(spacesItemDecoration);
        Log.d("space : ",String.valueOf(space));
    }

    protected void requestContentData(){
        MyGsonRequest<ApiResponse> myReq = new MyGsonRequest<ApiResponse>(this.getActivity(),
                Request.Method.POST,
                server_url,
                ApiResponse.class,
                networkSuccessListener(),
                networkErrorListener()) {
            protected Map<String,String> getParams() throws AuthFailureError{
                Map<String, String> params = new HashMap<String, String>();
                params.put("current_page",String.valueOf(page));
                params.put("theme_id","all");
                return params;
            }
        };

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
                            recyclerAdapter.dataAdd(i,response.getData().getGroups().get(0).getContents().get(i));
                            recyclerAdapter.notifyItemInserted(i);
                        }
                        Log.d("TITLE",String.valueOf(response.getData().getGroups().get(0).getContents()));
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
