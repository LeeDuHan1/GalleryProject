package com.example.btyisu.galleryproject;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.example.btyisu.galleryproject.statics.Const;
import com.example.btyisu.galleryproject.volley.MyGsonRequest;
import com.example.btyisu.galleryproject.volley.MyVolley;
import com.example.btyisu.galleryproject.adapter.NetRecyclerAdapter;
import com.example.btyisu.galleryproject.data.ApiResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LiveFragment extends Fragment {
    private RecyclerView mTumbnailRecyclerView = null;
    private NetRecyclerAdapter mRecyclerAdapter = null;
    private GridLayoutManager mLayoutManager = null;
    private SpacesItemDecoration mSpacesItemDecoration = null;
    private RequestQueue mRequestQueue = null;
    private int page = 1;
    private static LiveFragment mLiveFragmentInstance;

    public LiveFragment(){}

    public static LiveFragment getInstance(){
        if(mLiveFragmentInstance == null){
            mLiveFragmentInstance = new LiveFragment();
        }
        return mLiveFragmentInstance;
    }
//    public static LiveFragment getInstance(){
//        return LazyHolder.INSTANCE;
//    }
//    private static class LazyHolder{
//        private static final LiveFragment INSTANCE = new LiveFragment();
//    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setImageCount();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLayoutManager = new GridLayoutManager(getActivity(),2);
        mRequestQueue = MyVolley.getInstance(getActivity()).getRequestQueue();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        requestContentData();
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.live_fragment, container, false);
        initView(rootView);
        setRetainInstance(true);
        Toast.makeText(getActivity(),"onCreateView", Toast.LENGTH_SHORT).show();
        return rootView;
    }

    private void initView(View view){
        mTumbnailRecyclerView = (RecyclerView) view.findViewById(R.id.live_recycler_view);
        mTumbnailRecyclerView.setHasFixedSize(true); // to improve performance if you know that changes.
        mTumbnailRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mTumbnailRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL));
        mRecyclerAdapter = new NetRecyclerAdapter(getContext(), R.layout.content_live_grid_view,false);
        mTumbnailRecyclerView.setAdapter(mRecyclerAdapter);
        if(mTumbnailRecyclerView.getLayoutManager() == null) {
            mTumbnailRecyclerView.setLayoutManager(mLayoutManager);
        }
        setImageCount();
        /**
         * Scroll이 끝날때 총 item갯수와 RecyclerView의 마지막 인덱스를 비교하여
         * 스크롤의 마지막지점임을 판단하고 page를 1증가시켜 다음페이지 data를 요청합니다.
         */
        mTumbnailRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                GridLayoutManager gridLayoutManager = ((GridLayoutManager)recyclerView.getLayoutManager());
                int lastViewItem = gridLayoutManager.findLastVisibleItemPosition()+1;
                if(mRecyclerAdapter.getItemCount() == lastViewItem){
                    page += 1;
                    requestContentData();
                }
            }
        });
    }

    private void setImageCount(){
        int mDeviceWidth = getResources().getDisplayMetrics().widthPixels;
        int mSpanCount = mDeviceWidth/ Const.Size.THUMBNAIL_IMAGE_SIZE;
        int mSpace = (mDeviceWidth - (Const.Size.THUMBNAIL_IMAGE_SIZE * mSpanCount))/(mSpanCount * 2);
        mLayoutManager.setSpanCount(mSpanCount);
        mSpacesItemDecoration = new SpacesItemDecoration(mSpace);
        if(mTumbnailRecyclerView.getItemDecorationCount()>2){
            mTumbnailRecyclerView.removeItemDecorationAt(2);
        }
        mTumbnailRecyclerView.addItemDecoration(mSpacesItemDecoration);
    }

    /**
     * 아프리카 LIVE data를 불러오고 RequestQueue에 추가한다.
     * POST파라미터 값을 설정해준다.
     */
    protected void requestContentData(){
        MyGsonRequest<ApiResponse> myReq = new MyGsonRequest<ApiResponse>(this.getActivity(),
                Request.Method.POST,
                Const.Url.LIVE_URL,
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
        mRequestQueue.add(myReq);
    }

    /**
     * 데이터를 성공적으로 불러왔을경우 실행되며
     * RecyclerView에 들어가 있는 item수를 불러와서 해당 인덱스부터
     * 불러온 데이터수 인덱스까지 데이터를 추가시킨다.
     * adapter에 noti하는 부분은 adapter.dataAdd함수에 구현되어 있다.
     */
    private Response.Listener<ApiResponse> networkSuccessListener(){
        final String TAG = "networkSuccesListner";
        return new Response.Listener<ApiResponse>() {
            @Override
            public void onResponse(ApiResponse response) {
                String result = null;
                ArrayList<String> str = new ArrayList<>();
                    if (response != null) {
                        int count = response.getData().getGroups().get(0).size();
                        int adapterCount = mRecyclerAdapter.getItemCount();
                        for(int i=0; i< count;i++) {
                            mRecyclerAdapter.dataAdd(i+adapterCount, response.getData().getGroups().get(0).getContents().get(i));
                        }
                        Log.d("TITLE",String.valueOf(response.getData().getGroups().get(0).getContents()));
                    }
            }
        };
    }

    /**
     * 네트워크에러시 에러 Toast를 띄어줍니다.
     */
    private Response.ErrorListener networkErrorListener(){
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "network error", Toast.LENGTH_SHORT).show();
            }
        };
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mLayoutManager != null){
            mLayoutManager = null;
        }
    }
}
