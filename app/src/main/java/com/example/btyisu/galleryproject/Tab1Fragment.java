package com.example.btyisu.galleryproject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class Tab1Fragment extends Fragment{
    private Activity activity;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<String> dataSet;

    public Tab1Fragment(){

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
        GetFileList getFileList = new GetFileList(getActivity(),"flower");
        this.dataSet = getFileList.getTitleList();

        View view = inflater.inflate(R.layout.tab1_fragment, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.tab1_recycler_view);
        recyclerView.setHasFixedSize(true); // to improve performance if you know that changes.

        layoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(layoutManager);

        recyclerAdapter = new RecyclerAdapter(getContext());
        recyclerView.setAdapter(recyclerAdapter);
        return view;
    }


}
