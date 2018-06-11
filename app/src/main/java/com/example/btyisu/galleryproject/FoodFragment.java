package com.example.btyisu.galleryproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FoodFragment extends Fragment{
    private RecyclerView foodRecyclerView;
    private RecyclerView.Adapter recyclerAdapter;
    private RecyclerView.LayoutManager layoutManager;
    String[] dataSet = {"1","2","3","4","5"};

    public FoodFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.food_fragment, container, false);
        foodRecyclerView = (RecyclerView) view.findViewById(R.id.food_recycler_view);
        foodRecyclerView.setHasFixedSize(true); // to improve performance if you know that changes.

        layoutManager = new GridLayoutManager(getActivity(),2);
        foodRecyclerView.setLayoutManager(layoutManager);

        recyclerAdapter = new RecyclerAdapter(dataSet,getContext());
        foodRecyclerView.setAdapter(recyclerAdapter);
        return view;
    }

}
