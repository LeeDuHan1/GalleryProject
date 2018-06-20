package com.example.btyisu.galleryproject;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

public class NetRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public NetworkImageView imageView;
    private Context context;
    private ArrayList<String> dataSet;
    public NetRecyclerViewHolder(View view, Context context, ArrayList<String> dataSet){
        super(view);
        imageView = (NetworkImageView) view.findViewById(R.id.network_imageview);
        imageView.setOnClickListener(this);
        this.context = context;
        this.dataSet = dataSet;
    }

    @Override
    public void onClick(View view){
        Bundle bundle = new Bundle();
        bundle.putString("uri",dataSet.get(getAdapterPosition()));

        FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
        ImageDialogFragment imageDialogFragment = new ImageDialogFragment();
        imageDialogFragment.setArguments(bundle);
        imageDialogFragment.show(fragmentManager,"test");
    }

}
