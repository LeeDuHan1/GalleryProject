package com.example.btyisu.galleryproject;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    public TextView textview;

    public RecyclerViewHolder(View view){
        super(view);
        textview = (TextView) view.findViewById(R.id.textView3);
    }
}
