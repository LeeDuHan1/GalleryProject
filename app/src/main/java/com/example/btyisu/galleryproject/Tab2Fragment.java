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

import java.util.ArrayList;

public class Tab2Fragment extends Fragment{
    private Activity activity;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerAdapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<String> dataSet;
    int imageSize = 700;

    public Tab2Fragment(){
    }

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
        GetFileList getFileList = new GetFileList(getActivity(),"music");
        this.dataSet = getFileList.getTitleList();

        View view = inflater.inflate(R.layout.tab2_fragment, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.tab2_recycler_view);
        recyclerView.setHasFixedSize(true); // to improve performance if you know that changes.
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL));
        recyclerAdapter = new RecyclerAdapter(dataSet,activity);

        int width = getResources().getDisplayMetrics().widthPixels;
        int spanCount = width/imageSize;
        int space = (width - (imageSize*spanCount))/(spanCount*2);
        Log.d("space", space+"");
        layoutManager = new GridLayoutManager(getActivity(),spanCount);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.addItemDecoration(new SpacesItemDecoration(space));

        return view;
    }


}
