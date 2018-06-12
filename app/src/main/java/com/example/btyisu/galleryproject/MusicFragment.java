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

public class MusicFragment extends Fragment{
    private RecyclerView musicRecyclerView;
    private RecyclerView.Adapter recyclerAdapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<String> dataSet = new ArrayList<>();
    public MusicFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        GetFileList getFileList = new GetFileList(getActivity(),"music");
        this.dataSet = getFileList.getTitleList();

        View view = inflater.inflate(R.layout.music_fragment, container, false);
        musicRecyclerView = (RecyclerView) view.findViewById(R.id.music_recycler_view);
        musicRecyclerView.setHasFixedSize(true); // to improve performance if you know that changes.

        layoutManager = new GridLayoutManager(getActivity(),2);
        musicRecyclerView.setLayoutManager(layoutManager);

        recyclerAdapter = new RecyclerAdapter(dataSet,getContext());
        musicRecyclerView.setAdapter(recyclerAdapter);
        return view;
    }


}
