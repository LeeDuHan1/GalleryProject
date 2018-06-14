package com.example.btyisu.galleryproject;

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

public class Tab2Fragment extends Fragment{
    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerAdapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<String> dataSet = new ArrayList<>();
    public Tab2Fragment(){
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        GetFileList getFileList = new GetFileList(getActivity(),"aircraft");
        this.dataSet = getFileList.getTitleList();

        View view = inflater.inflate(R.layout.tab2_fragment, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.tab2_recycler_view);
        recyclerView.setHasFixedSize(true); // to improve performance if you know that changes.

        layoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(layoutManager);

        recyclerAdapter = new RecyclerAdapter(getContext());
        recyclerView.setAdapter(recyclerAdapter);
        return view;
    }


}
