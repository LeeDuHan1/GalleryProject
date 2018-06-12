package com.example.btyisu.galleryproject;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;

    public RecyclerViewHolder(View view){
        super(view);
        imageView = (ImageView) view.findViewById(R.id.imageView);
    }
}
