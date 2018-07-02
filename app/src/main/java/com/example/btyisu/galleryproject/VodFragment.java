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

public class VodFragment extends Fragment {
    private RecyclerView mThumbnailRecyclerView = null;
    private NetRecyclerAdapter mRecyclerAdapter = null;
    private GridLayoutManager mLayoutManager = null;
    private SpacesItemDecoration mSpacesItemDecoration = null;
    private RequestQueue mRequestQueue = null;
    private int page = 1;
    private static VodFragment mVodFragmentInstance;
    public VodFragment(){}

    public static VodFragment getInstance(){
        if(mVodFragmentInstance == null){
            mVodFragmentInstance = new VodFragment();
        }
        return mVodFragmentInstance;
    }

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
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.vod_fragment, container, false);
        initView(rootView);
        setRetainInstance(true);
        Toast.makeText(getActivity(),"onCreateView", Toast.LENGTH_SHORT).show();
        return rootView;
    }

    private void initView(View view){
        mThumbnailRecyclerView = (RecyclerView) view.findViewById(R.id.vod_recycler_view);
        mThumbnailRecyclerView.setHasFixedSize(true); // to improve performance if you know that changes.
        mThumbnailRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mThumbnailRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL));
        mRecyclerAdapter = new NetRecyclerAdapter(getContext(), R.layout.content_vod_grid_view,true);
        mThumbnailRecyclerView.setAdapter(mRecyclerAdapter);
        mThumbnailRecyclerView.setLayoutManager(mLayoutManager);
        mThumbnailRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
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
        setImageCount();
    }

    private void setImageCount(){
        int deviceWidth = getResources().getDisplayMetrics().widthPixels;
        int spanCount = deviceWidth / Const.Size.THUMBNAIL_IMAGE_SIZE;
        int space = (deviceWidth - (Const.Size.THUMBNAIL_IMAGE_SIZE * spanCount))/(spanCount * 2);
        mLayoutManager.setSpanCount(spanCount);
        mSpacesItemDecoration = new SpacesItemDecoration(space);
        if(mThumbnailRecyclerView.getItemDecorationCount()>2){
            mThumbnailRecyclerView.removeItemDecorationAt(2);
        }

        mThumbnailRecyclerView.addItemDecoration(mSpacesItemDecoration);
    }
    /**
     * 아프리카 VOD data를 불러오고 RequestQueue에 추가한다.
     * POST파라미터 값을 설정해준다.
     */
    protected void requestContentData(){
        MyGsonRequest<ApiResponse> myReq = new MyGsonRequest<ApiResponse>(this.getActivity(),
                Request.Method.POST,
                Const.Url.VOD_URL,
                ApiResponse.class,
                networkSuccessListener(),
                networkErrorListener()) {
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("szPlatform","mobile");
                params.put("current_page",String.valueOf(page));
                params.put("v","1.0");
                params.put("nListCnt","20");
                params.put("szOrder","view_cnt");
                params.put("szTerm","1week");
                params.put("nPageNo",String.valueOf(page));
                params.put("rows_per_page","30");
                params.put("szCateNo","00000000");
                params.put("m","vodList");
                params.put("align_type","grid");
                Log.d("페이지",String.valueOf(page));
//                params.put("theme_id","later");
                return params;
            }
        };
        mRequestQueue.add(myReq);
    }
    /**
     * 데이터를 성공적으로 불러왔을경우 실행되며
     * RecyclerView에 들어가 있는 item수를 불러와서 해당 인덱스부터
     * 불러온 데이터수 인덱스까지 데이터를 추가시킨다.
     * adapter에 noti하는 부분은 NetRecyclerViewAapter.dataAdd함수 부분에 구현되어 있습니다..
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
                        mRecyclerAdapter.dataAdd(i+adapterCount,response.getData().getGroups().get(0).getContents().get(i));
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
                Toast.makeText(getContext(), "network error", Toast.LENGTH_SHORT).show();
            }
        };
    }
    public void onDestroy() {
        super.onDestroy();
        if(mLayoutManager != null){
            mLayoutManager = null;
        }
    }

}